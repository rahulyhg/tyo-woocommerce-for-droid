/*
 * Copyright (c) 2017 TYONLINE TECHNOLOGY PTY. LTD. (TYO Lab)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package au.com.tyo.inventory.model;

import com.google.gson.internal.LinkedTreeMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Tang (eric.tang@tyo.com.au) on 13/12/17.
 */

public class Product extends GeneralItem {

    private String imageUrl;

    public enum ProductAttributeType {SELECT, TEXT};

    public String getImageUrl() {
        if (imageUrl == null) {
            List object = (List) get("images");
            if (null != object && object.size() > 0) {
                LinkedTreeMap imageJson = (LinkedTreeMap) object.get(0);
                imageUrl = (String) imageJson.get("src");
            }
        }
        return imageUrl;
    }

    public String getSku() {
        return (String) get("sku");
    }

    public int getStock() {
        return getInt("stock_quantity");
    }

    public void setStock(int stock) {
        put("stock_quantity", stock);
    }

    public float getPrice() {
        return getFloatString("regular_price");
    }

    public void setPrice(String price) {
        put("regular_price", price);
    }

    public String getPermalink() {
        return (String) get("permalink");
    }

    public void setProductTypeSimple() {
        put("type", "simple");
    }

    public void setDescription(String desc) {
        put("description", desc);
    }

    public void setCategory(int catId) {
        List list = getListData("categories");

        Map map = createMapData();
        map.put("id", catId);
        list.add(map);
    }

    public void setAttribute(String name, String... options) {
        setAttribute(name, true, options);
    }

    public void setAttribute(String name, boolean visible, String... options) {
        List list = getListData("attributes");

        Map map = createMapData();
        map.put("name", name);
        map.put("visible", visible);

        List optionList = getListData(map, "options");

        for (String option : options)
            optionList.add(option);

        list.add(map);
    }

    public void setProductAttribute(String name, ProductAttributeType type) {
        List list = getListData("product_attributes");

        Map map = createMapData();
        map.put("name", name);

        String typeStr;
        if (type == ProductAttributeType.SELECT)
            typeStr = "select";
        else
            typeStr = "text";

        map.put("type", typeStr);

        list.add(map);
    }

    public void setImage(String imageUrl) {
        Map map = createMapData("src", imageUrl);
        List list = getListData("images");
        map.put("position", list.size());
        list.add(map);
    }

    public void setInStock(boolean inStock) {
        put("in_stock", inStock);
    }
}
