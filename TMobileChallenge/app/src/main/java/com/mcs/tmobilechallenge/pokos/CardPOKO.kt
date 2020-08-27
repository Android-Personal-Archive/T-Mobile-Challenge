package com.mcs.tmobilechallenge.pokos
//todo not sure if this will work with value since it could be null.  If error make String?.
//not sure if this will work without value and attributes since title and description are the same
//  however, the fact title and description both exist at the same time introduces an ambiguity if chosen
//  however, since the key is not equal it will not match the json value and attributes keys anyway
//  so, I believe value and attributes are required
//  still, double check if String should be null-safe (String?)
data class CardPOKO(val value : String, val attributes : AttributePOKO, val image : ImagePOKO, val title : TitDesPOKO, val description : TitDesPOKO)
