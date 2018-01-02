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

import com.google.api.client.json.GenericJson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * Created by Eric Tang (eric.tang@tyo.com.au) on 13/12/17.
 */

public class Product extends GenericJson  {

    private String imageUrl;

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

    public int getId() {
        return getInt("id");
    }

    public int getSku() {
        return getInt("sku");
    }

    private int getInt(String key) {
        try {
            return Integer.parseInt((String) get(key));
        }
        catch (Exception ex) {}
        return -1;
    }

    public int getStock() {
        return getInt("stock_quantity");
    }

}
