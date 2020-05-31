package com.ego.search.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.JsonUtils;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import com.ego.provider.service.ItemDescService;
import com.ego.provider.service.ItemService;
import com.ego.provider.service.ParamItemService;
import com.ego.search.service.TbItemSearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TbItemSearchServiceImpl implements TbItemSearchService {
    @Reference
    private ItemService itemService;
    @Reference
    private ItemDescService itemDescService;
    @Reference
    private ParamItemService paramItemService;

    @Override
    public TbItem findById(long id) {
        try {
            return itemService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String itemDesc(long id) {
        TbItemDesc itemDesc = itemDescService.getItemDesc(id);
        return itemDesc.getItemDesc();
    }

    @Override
    public String itemParamItem(long cid) {
        TbItemParamItem paramItem = paramItemService.findParamItem(cid);
        String paramData = JsonUtils.objectToJson(paramItem);
        if (paramItem != null) {
            return getItemParamData(paramData);

        } else {
            return "<span>该商品没用规格参数</span>";
        }
    }

    private String getItemParamData(String paramData) {
        //解析json字符串
        List<Map> listMap = JsonUtils.jsonToList(paramData, Map.class);

        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map m1 : listMap) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"
                    + m1.get("group") + "</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for (Map m2 : list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">" + m2.get("k")
                        + "</td>\n");
                sb.append("            <td>" + m2.get("v") + "</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        // 返回html片段
        return sb.toString();

    }
}
