package com.visiable.convert.convertmodule;

import java.util.ArrayList;
import java.util.List;

import org.gdal.gdal.GCP;
import org.gdal.gdal.gdal;
import org.gdal.osr.CoordinateTransformation;
import org.gdal.osr.SpatialReference;
import org.json.JSONArray;
import org.json.JSONObject;

import com.gml.primalspace.CellSpaceMember;
import com.gml.primalspace.IndoorFeatures;
import com.gml.primalspace.Pos;
import com.gml.primalspace.SurfaceMember;
import com.visiable.convert.jaxb.Jaxb;

public class Convert {

	public static JSONObject convertCoordinate(JSONArray coordinates, IndoorFeatures indoorFeatures) {
		ArrayList<GCP> listGCP = new ArrayList<GCP>();

		for (Object element : coordinates) {
			JSONObject jsonObject = (JSONObject) element;
			String[] tokens = jsonObject.get("coordinate").toString().split(",");
			
			double longi = Double.parseDouble(tokens[0]);
			double lati = Double.parseDouble(tokens[1]);
			double Xpixel = Double.parseDouble(tokens[2]);
			double Yline = Double.parseDouble(tokens[3]);
			
			
			listGCP.add(new GCP(longi, lati, Xpixel, Yline));
		}

		double myGT[] = new double[6];
		GCP[] arrayGCP = listGCP.toArray(new GCP[listGCP.size()]);

		gdal.GCPsToGeoTransform(arrayGCP, myGT, 0);

		List<CellSpaceMember> cellSpaceMembers = indoorFeatures.getPrimalSpaceFeatures().getPrimalSpaceFeatures()
				.getCellSpaceMember();
		
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArray2 = new JSONArray();
		JSONArray jsonArray3 = new JSONArray();
		
		for (CellSpaceMember cellSpaceMember : cellSpaceMembers) {
			List<SurfaceMember> surfaceMembers = cellSpaceMember.getCellSpace().getCellSpaceGeometry().getGeometry3d()
					.getSolid().getExterior().getShell().getSurfaceMembers();
			int index = 0;
			for (SurfaceMember surfaceMember : surfaceMembers) {
				List<Pos> posList = surfaceMember.getPolygon().getExterior().getPos();
				
				jsonArray = new JSONArray();
				jsonArray2 = new JSONArray();
				
				for (Pos pos : posList) {
					String[] tokens = pos.getSmallCommaVector().split(",");

					double Xpoint = Double.parseDouble(tokens[0]);
					double Yline = Double.parseDouble(tokens[1]);

					double resultX = myGT[0] + Xpoint * myGT[1] + Yline * myGT[2];
					double resultY = myGT[3] + Xpoint * myGT[4] + Yline * myGT[5];

					SpatialReference s_srs = new SpatialReference();
					SpatialReference t_srs = new SpatialReference();
					s_srs.ImportFromEPSG(4326);
					t_srs.ImportFromEPSG(3857);

					CoordinateTransformation ct = CoordinateTransformation.CreateCoordinateTransformation(s_srs, t_srs);

					double[] epsg3857 = ct.TransformPoint(resultX, resultY);
					
					//pos.changeX(resultX);
					//pos.changeY(resultY);
					
					
					pos.changeX(epsg3857[0]);
					pos.changeY(epsg3857[1]);
					
					
					pos.changeVector();
					
					
					
					
					double[] point = new double[2];
					point[0] = epsg3857[0];
					point[1] = epsg3857[1];
					
					
					if (index == 0) {
						jsonArray.put(point);											
					}
				}
				if (index == 0) {
					jsonArray2.put(jsonArray);
					jsonArray3.put(new JSONObject().put("polygon",jsonArray2));											
				}
				
				index++;
			}
		}

		Jaxb.marshall(indoorFeatures);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("polygons", jsonArray3);
		return jsonObject;
	}
	
	public static JSONArray makeConvertedPolygon(JSONArray coordinates, IndoorFeatures indoorFeatures) {
		ArrayList<GCP> listGCP = new ArrayList<GCP>();

		for (Object element : coordinates) {
			JSONObject jsonObject = (JSONObject) element;
			String[] tokens = jsonObject.get("coordinate").toString().split(",");
			
			double longi = Double.parseDouble(tokens[0]);
			double lati = Double.parseDouble(tokens[1]);
			double Xpixel = Double.parseDouble(tokens[2]);
			double Yline = Double.parseDouble(tokens[3]);
			
			listGCP.add(new GCP(longi, lati, Xpixel, Yline));
		}
		double myGT[] = new double[6];
		GCP[] arrayGCP = listGCP.toArray(new GCP[listGCP.size()]);

		gdal.GCPsToGeoTransform(arrayGCP, myGT,0);
		
		List<CellSpaceMember> cellSpaceMember = indoorFeatures.getPrimalSpaceFeatures().getPrimalSpaceFeatures().getCellSpaceMember();
		List<SurfaceMember> surfaceMembers = new ArrayList<SurfaceMember>();
		
		for (CellSpaceMember element : cellSpaceMember) {
			surfaceMembers.add(element.getCellSpace().getCellSpaceGeometry().getGeometry3d().getSolid().getExterior().getShell().getSurfaceMembers().get(0));
		}
		
		List<Pos> posList = new ArrayList<Pos>();
		
		for (SurfaceMember element : surfaceMembers) {
			posList.addAll(element.getPolygon().getExterior().getPos());
		}
		
		for (Pos pos : posList) {
			String[] tokens = pos.getSmallCommaVector().split(",");

			double Xpoint = Double.parseDouble(tokens[0]);
			double Yline = Double.parseDouble(tokens[1]);

			double resultX = myGT[0] + Xpoint * myGT[1] + Yline * myGT[2];
			double resultY = myGT[3] + Xpoint * myGT[4] + Yline * myGT[5];

			SpatialReference s_srs = new SpatialReference();
			SpatialReference t_srs = new SpatialReference();
			s_srs.ImportFromEPSG(4326);
			t_srs.ImportFromEPSG(3857);

			CoordinateTransformation ct = CoordinateTransformation.CreateCoordinateTransformation(s_srs, t_srs);

			double[] epsg3857 = ct.TransformPoint(resultX, resultY);
			
			


			//pos.changeX(resultX);
			//pos.changeY(resultY);
			
			pos.changeX(epsg3857[0]);
			pos.changeY(epsg3857[1]);
			
			pos.changeVector();
		}
		
		for (Pos pos : posList) {
			System.out.println(pos.getSmallCommaVector());
		}
		
		return null;
	}
}
