package com.freshtuna.tooth.api.response;

import java.util.HashMap;

abstract class HalAbstractResponse {

    private val _links = HashMap<String, String>()

    companion object {
        private val SELF_RELATION = "self";
    }

    fun addLink(relation: String, url: String) {
        this._links[relation] = url;
    }

    fun addSelfLink(url: String) {
        addLink(SELF_RELATION, url);
    }
}
