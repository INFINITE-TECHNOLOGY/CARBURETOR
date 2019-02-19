package io.infinite.carburetor


import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true)
//@compilestatic
class HttpMessageAbstract {

    HashMap<String, String> headers = new HashMap<>()
    String body

}
