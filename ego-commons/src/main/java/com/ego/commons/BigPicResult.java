package com.ego.commons;

/**
 * 接收大广告数据
 */
public class BigPicResult {
/*"width": 310,
        "height": 70,
        "src": "/images/5440ce68Na00d019e.jpg",
        "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3614&bid=4196&unit=35984&advid=109277&guv=&url=http://sale.jd.com/mall/FQLUNlG53wbX7m.html",
        "alt": "",
        "widthB": 210,
        "heightB": 70,
        "srcB": "*/
    private int width;
    private int widthB;
    private int height;
    private int heightB;

    private String src;
    private String srcB;
    private String alt;
    private String href;

    public int getWidth() {
        return this.width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getWidthB() {
        return this.widthB;
    }

    public void setWidthB(final int widthB) {
        this.widthB = widthB;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getHeightB() {
        return this.heightB;
    }

    public void setHeightB(final int heightB) {
        this.heightB = heightB;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(final String src) {
        this.src = src;
    }

    public String getSrcB() {
        return this.srcB;
    }

    public void setSrcB(final String srcB) {
        this.srcB = srcB;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(final String alt) {
        this.alt = alt;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(final String href) {
        this.href = href;
    }
}
