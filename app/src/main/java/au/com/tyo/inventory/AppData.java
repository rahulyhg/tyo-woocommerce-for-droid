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

package au.com.tyo.inventory;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import au.com.tyo.app.CommonAppData;
import au.com.tyo.inventory.model.ProductListItem;
import au.com.tyo.woocommerce.WooCommerceApi;
import au.com.tyo.woocommerce.WooCommerceJson;

/**
 * Created by Eric Tang (eric.tang@tyo.com.au) on 13/12/17.
 */

public class AppData extends CommonAppData {

    private static final String TAG = "AppData";
    //
    // API
    //
    private WooCommerceApi api;

    //
    // PARSER
    //
    private WooCommerceJson parser;

    //
    // MODELS
    //
    private List<ProductListItem> products;


    public AppData(Context context) {
        super(context);

        this.api = new WooCommerceApi(context);
        this.parser = new WooCommerceJson();
    }

    public List<ProductListItem> getProducts() {
        return products;
    }

    public WooCommerceApi getApi() {
        return api;
    }

    public void loadProducts() {
        String json = api.getProductsJsonString();

        Type collectionType = new TypeToken<List<ProductListItem>>(){}.getType();
        products = WooCommerceJson.getGson().fromJson(json, collectionType);

        Log.d(TAG, "products loaded: total " + products.size());
    }
}
