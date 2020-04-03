package com.visiable.convert.jaxb;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class DefaultNamespacePrefixMapper extends NamespacePrefixMapper {
	private Map<String, String> namespaceMap = new HashMap<>();
	
	public DefaultNamespacePrefixMapper() {
		namespaceMap.put("http://www.opengis.net/gml/3.2", "gml");
		namespaceMap.put("http://www.w3.org/1999/xlink", "xlink");
		namespaceMap.put("http://www.opengis.net/indoorgml/1.0/core", "core");
		namespaceMap.put("http://www.opengis.net/indoorgml/1.0/navigation", "navi");
		namespaceMap.put("http://indoorgml.net/repository/NonNaviSpace", "nonnavi");
		namespaceMap.put("http://indoorgml.net/extensions/textureext", "ns6");
		namespaceMap.put("http://indoorgml.net/extensions/PSExt", "ns7");
		namespaceMap.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
	}
	
	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		return namespaceMap.getOrDefault(namespaceUri, suggestion);
	}
	
}