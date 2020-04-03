package com.visiable.convert.jaxb;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.JSONObject;

import com.gml.primalspace.CellSpaceMember;
import com.gml.primalspace.IndoorFeatures;
import com.gml.primalspace.Pos;
import com.gml.primalspace.PrimalSpaceFeatures;
import com.gml.primalspace.SurfaceMember;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class Jaxb {
	public static IndoorFeatures unmarshall(Path filePath){
		
		IndoorFeatures indoorFeatures = null;
		JAXBContext context = null;
		
		try {
			context = JAXBContext.newInstance(IndoorFeatures.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		try {
			indoorFeatures =  (IndoorFeatures) context.createUnmarshaller().unmarshal(filePath.toFile());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return indoorFeatures;
	}
	
	public static void marshall(IndoorFeatures indoorFeatures) {
		JAXBContext context;
		Marshaller marshaller;
		
		
		try {
			context = JAXBContext.newInstance(IndoorFeatures.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
			//marshaller.setSchema(arg0);
			marshaller.marshal(indoorFeatures, new FileOutputStream("./uploadedIndoorGML/converted_indoorGML.gml"));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject getConvertingParamPos(IndoorFeatures indoorFeatures){
		List<CellSpaceMember> cellSpaceMember = indoorFeatures.getPrimalSpaceFeatures().getPrimalSpaceFeatures().getCellSpaceMember();
		List<SurfaceMember> surfaceMembers = new ArrayList<SurfaceMember>();
		
		for (CellSpaceMember element : cellSpaceMember) {
			surfaceMembers.add(element.getCellSpace().getCellSpaceGeometry().getGeometry3d().getSolid().getExterior().getShell().getSurfaceMembers().get(0));
		}
		
		Pos left = null;
		Pos right = null;
		Pos top = null;
		Pos bottom = null;
		
		List<Pos> pos = new ArrayList<Pos>();
		
		for (SurfaceMember element : surfaceMembers) {
			pos.addAll(element.getPolygon().getExterior().getPos());
		}
		
		
		for (Pos element : pos) {
			if (left == null || left.getX() > element.getX()) left = element;
			if (right == null || right.getX() < element.getX()) right = element;
			if (top == null || top.getY() < element.getY()) top = element;
			if (bottom == null || bottom.getY() > element.getY()) bottom = element;
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("left", left.getSmallCommaVector());
		jsonObject.put("right", right.getSmallCommaVector());
		jsonObject.put("top", top.getSmallCommaVector());
		jsonObject.put("bottom", bottom.getSmallCommaVector());

		return jsonObject;
	}
	
}
