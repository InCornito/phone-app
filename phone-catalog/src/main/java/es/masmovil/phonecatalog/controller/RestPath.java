package es.masmovil.phonecatalog.controller;

interface RestPath {

    String BASE = "/phones";
    String ROOT = "/";
    String ID = "/{" + RestParams.ID + "}";
    String BATCH_PRICES = "/batch-prices";
}
