package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import carItem.CarItem;
import com.ego.commons.ItemStatus;
import com.ego.commons.JsonUtils;
import com.ego.item.service.CarItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbUser;
import com.ego.provider.service.ItemService;
import com.ego.redis.jedisdao.JedisClusterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarItemServiceImpl implements CarItemService {
    @Autowired
    private JedisClusterDao jedisClusterDao;
    @Reference
    private ItemService itemService;

    @Override
    public void addCarItem(TbUser user, long itemid) {
        //获取登录用户信息
        String uid = String.valueOf(user.getId());
        String username = user.getUsername();
        //定义封装购物车的集合
        List<CarItem> carList = new ArrayList<>();
        //声明封装商品对象的购物车对象
        CarItem car =null;
        //查询redis中是否有购物车对象
        boolean exists = jedisClusterDao.exists(uid + ":" + username);
        if(exists){//如果有
            //获取购物车对象并转换成封装购物车的集合
            String carStr = jedisClusterDao.get(uid + ":" + username);
            if(!StringUtils.isEmpty(carStr)){
                carList = JsonUtils.jsonToList(carStr, CarItem.class);
                for(CarItem carItem:carList){
                    //如果购物车中的商品id等于传进来的itemid
                    long id = carItem.getTbItem().getId();
                    if (id==itemid){
                        carItem.setNum(carItem.getNum()+1);
                        String carItemList = JsonUtils.objectToJson(carList);
                        jedisClusterDao.set(uid+":"+username,carItemList);
                        return;
                    }
                }
            }
        }
        car = new CarItem();
        //查询商品对象
        TbItem item = itemService.findById(itemid);
        item.setImages(item.getImage()==null||item.getImage().equals("")?new String[1]:item.getImages());
        //把商品和数量放入购物车对象
        car.setTbItem(item);
        car.setNum(1);
        carList.add(car);

        //把封装购物车的集合转换成json格式的字符串并存入redis
        String carItemList = JsonUtils.objectToJson(carList);
        jedisClusterDao.set(uid+":"+username,carItemList);
    }

    /**
     * 显示商品
     * @param user
     * @return
     */
    @Override
    public List<TbItem> findCarItem(TbUser user) {
        String uid = String.valueOf(user.getId());
        String username = user.getUsername();
        String carStr = jedisClusterDao.get(uid + ":" + username);
        List<CarItem> carItems = JsonUtils.jsonToList(carStr, CarItem.class);
        List<TbItem> tbItemList = new ArrayList<>();
        for(CarItem carItem:carItems){
            TbItem tbItem = carItem.getTbItem();
            tbItem.setNum(carItem.getNum());
            tbItemList.add(tbItem);
        }
        return tbItemList;
    }

    /**
     * 商品数量的增减
     * @param user
     * @param itemid
     * @param num
     */
    @Override
    public void updateNum(TbUser user, long itemid, Integer num) {
        String uid = String.valueOf(user.getId());
        String username = user.getUsername();
        String carStr = jedisClusterDao.get(uid + ":" + username);
        List<CarItem> carItems = JsonUtils.jsonToList(carStr, CarItem.class);
        for(CarItem carItem:carItems){
            TbItem tbItem = carItem.getTbItem();
            if(tbItem.getId()==itemid){
                carItem.setNum(num);
                String carItemList = JsonUtils.objectToJson(carItems);
                jedisClusterDao.set(uid+":"+username,carItemList);
                return ;
            }
        }
    }

    @Override
    public ItemStatus deleteItem(TbUser user, long itemid) {
        String uid = String.valueOf(user.getId());
        String username = user.getUsername();
        ItemStatus is = new ItemStatus();
        try {
            String carStr = jedisClusterDao.get(uid + ":" + username);
            List<CarItem> carItems = JsonUtils.jsonToList(carStr, CarItem.class);
            CarItem car = null;
            for(CarItem carItem:carItems){
                TbItem tbItem = carItem.getTbItem();
                if(tbItem.getId()==itemid){
                    car = carItem;
                }
            }
            carItems.remove(car);
            String carItemList = JsonUtils.objectToJson(carItems);
            jedisClusterDao.set(uid+":"+username,carItemList);
            is.setStatus(200);
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }
}
