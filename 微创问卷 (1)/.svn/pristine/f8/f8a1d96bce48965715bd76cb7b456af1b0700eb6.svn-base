package com.questionnaire.controller;

import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import com.questionnaire.pojo.ClinicalApplication;
import com.questionnaire.pojo.Consumer;
import com.questionnaire.pojo.Equipment;
import com.questionnaire.pojo.Hospital;
import com.questionnaire.pojo.Ks;

import com.questionnaire.pojo.dto.ClinicalApplicationDto;
import com.questionnaire.pojo.dto.EquipmentTto;
import com.questionnaire.pojo.dto.PageDto;
import com.questionnaire.pojo.dto.StaffDto;
import com.questionnaire.service.ClinicalApplicationService;
import com.questionnaire.service.EquipmentService;
import com.questionnaire.service.HospitalService;
import com.questionnaire.service.KsService;
import com.questionnaire.service.UserService;
import com.questionnaire.util.CommonUtils;
import com.questionnaire.util.TempltUtil;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@RequestMapping("/question")
@Controller
public class QuestionController {

	@Resource
	private HospitalService hospitalService;
	@Resource
	private EquipmentService equipmentService;
	@Resource
	private KsService ksService;
	@Resource
	private ClinicalApplicationService clinicalApplicationService;
	@Resource
	private UserService userService;
	
	// 问卷列表
	@RequestMapping("questionList")
	public String questionList(Map<String, Object> out, Integer index,  HttpServletRequest request) {
		Consumer user = (Consumer) request.getSession().getAttribute("consumer");
		out.put("user", user);
		PageDto<Hospital> page = new PageDto<Hospital>();
		if (index == null) {
			index = 1;
		}
		page.setCurrentPage(index);
		page.setPageSize(20);
		page = hospitalService.pageHospital(page);
		out.put("page", page);
		return "question/questionList";
	}

	// 首页
	@RequestMapping("questionDetail")
	public String index(Map<String, Object> out, Integer hospitalId) {
		if (!CommonUtils.isEmpty(hospitalId)) {
			// 医院基本情况
			Hospital hp = new Hospital();
			hp.setId(hospitalId);
			Hospital hospital = hospitalService.findHospital(hp);
			out.put("hospital", hospital);

			// 人员信息			
            List<StaffDto> staffList = staff(hospitalId);
		    out.put("staffList", staffList);

			// 设备信息
			EquipmentTto equipment = equipment(hospitalId);
			out.put("equipment", equipment);

			// 临床应用
			ClinicalApplicationDto clinicalApplication = clinicalApplication(hospitalId);
			out.put("clinicalApplication", clinicalApplication);
	
		}
		return "question/questionDetail";
	}
	
	private ClinicalApplicationDto clinicalApplication(Integer hospitalId){
		ClinicalApplication ca = new ClinicalApplication();
		ClinicalApplicationDto clinicalApplication = new ClinicalApplicationDto();
		ca.setHospitalId(hospitalId);
		ClinicalApplication clinical = clinicalApplicationService.findClinicalApplication(ca);
		if (!CommonUtils.isEmpty(clinical)) {
			String operation = clinical.getOperation();
			JSONObject opera = JSONObject.parseObject(operation);
			String fqLocalSurgery = opera.getString("fqLocalSurgery");
			String fqLeftSurgery = opera.getString("fqLeftSurgery");
			String fqLeftSideSurgery = opera.getString("fqLeftSideSurgery");
			String fqRightSideSurgery = opera.getString("fqRightSideSurgery");
			String fqSurgery[] = {fqLocalSurgery,fqLeftSurgery,fqLeftSideSurgery,fqRightSideSurgery};
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < fqSurgery.length; i++) {
				if(!CommonUtils.isEmpty(fqSurgery[i])){
					buf.append(fqSurgery[i]).append("、");
				}			    
			}				
			if (buf.length() > 0) {			    
				clinicalApplication.setFqLocalSurgery(buf.deleteCharAt(buf.length()-1).toString());
			}

			String fqsimilarProportion = clinical.getFqsimilarProportion();
			JSONObject fqsimilar = JSONObject.parseObject(fqsimilarProportion);
			String gndgProportion = fqsimilar.getString("gndgProportion");
			String gzlProportion = fqsimilar.getString("gzlProportion");
			clinicalApplication.setGndgProportion(gndgProportion);
			clinicalApplication.setGzlProportion(gzlProportion);

			String speedKfsurgical = clinical.getSpeedKfsurgical();
			JSONObject kfsurgical = JSONObject.parseObject(speedKfsurgical);
			String kfProportion = kfsurgical.getString("kfProportion");
			String proportion = kfsurgical.getString("proportion");
			clinicalApplication.setKfProportion(kfProportion);
			clinicalApplication.setProportion(proportion);

			String minimallyWhy = clinical.getMinimallyWhy();
			JSONObject minimally = JSONObject.parseObject(minimallyWhy);
			String medical = minimally.getString("medical");
			String cmDisease = minimally.getString("cmDisease");
			String lackDoctor = minimally.getString("lackDoctor");
			String lackEquipment = minimally.getString("lackEquipment");
			String otherReasons = minimally.getString("otherReasons");
			String reasons = minimally.getString("reasons");
			
			String mally[] = {medical,cmDisease,lackDoctor,lackEquipment,reasons};
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < mally.length; i++) {
				if(!CommonUtils.isEmpty(mally[i])){
					buffer.append(mally[i]).append("、");
				}			    
			}				
			if (buffer.length() > 0) {			    
				clinicalApplication.setMedical(buffer.deleteCharAt(buffer.length()-1).toString());
			}
			clinicalApplication.setOtherReasons(otherReasons);

			String zsurgeryProportion = clinical.getZsurgeryProportion();
			JSONObject zp = JSONObject.parseObject(zsurgeryProportion);
			String zkfProportion = zp.getString("zkfProportion");
			String fqldNumber = zp.getString("fqldNumber");
			String ddssNumber = zp.getString("ddssNumber");
			String bleedingNumber = zp.getString("bleedingNumber");
			String dzgjsNumber = zp.getString("dzgjsNumber");
			String otherNumber = zp.getString("otherNumber");
			clinicalApplication.setZkfProportion(zkfProportion);
			clinicalApplication.setFqldNumber(fqldNumber);
			clinicalApplication.setDdssNumber(ddssNumber);
			clinicalApplication.setBleedingNumber(bleedingNumber);
			clinicalApplication.setDzgjsNumber(dzgjsNumber);
			clinicalApplication.setOtherNumber(otherNumber);

			String zsurgery = clinical.getZsurgery();
			JSONObject zg = JSONObject.parseObject(zsurgery);
			String operationTime = zg.getString("operationTime");
			String bleedingMl = zg.getString("bleedingMl");
			String portazsNumber = zg.getString("portazsNumber");
			String portazsTime = zg.getString("portazsTime");
			clinicalApplication.setOperationTime(operationTime);
			clinicalApplication.setBleedingMl(bleedingMl);
			clinicalApplication.setPortazsNumber(portazsNumber);
			clinicalApplication.setPortazsTime(portazsTime);

			String complications = clinical.getComplications();
			JSONObject complica = JSONObject.parseObject(complications);
			String twoSurgeryNumber = complica.getString("twoSurgeryNumber");
			String bfzBleedingNumber = complica.getString("bfzBleedingNumber");
			String bfzFistulaNumber = complica.getString("bfzFistulaNumber");
			String bfzQkgrNumber = complica.getString("bfzQkgrNumber");
			String bfzFqgrNumber = complica.getString("bfzFqgrNumber");
			String bfzOtherNumber = complica.getString("bfzOtherNumber");
			clinicalApplication.setTwoSurgeryNumber(twoSurgeryNumber);
			clinicalApplication.setBfzBleedingNumber(bfzBleedingNumber);
			clinicalApplication.setBfzFistulaNumber(bfzFistulaNumber);
			clinicalApplication.setBfzQkgrNumber(bfzQkgrNumber);
			clinicalApplication.setBfzFqgrNumber(bfzFqgrNumber);
			clinicalApplication.setBfzOtherNumber(bfzOtherNumber);
		}
		
		return clinicalApplication;
	}

	private EquipmentTto equipment(Integer hospitalId) {
		Equipment eq = new Equipment();
		EquipmentTto equipment = new EquipmentTto();
		eq.setHospitalId(hospitalId);
		Equipment emp = equipmentService.findEquipment(eq);
		if (!CommonUtils.isEmpty(emp)) {
			String qiangjinji = emp.getQiangjinji();
			JSONObject qjiObject = JSONObject.parseObject(qiangjinji);
			String qNumber = qjiObject.getString("qNumber");
			String qImportNumber = qjiObject.getString("qImportNumber");
			String qImportBrand = qjiObject.getString("qImportBrand");
			String qDomesticNumber = qjiObject.getString("qDomesticNumber");
			String qDomesticBrand = qjiObject.getString("qDomesticBrand");
			equipment.setqNumber(qNumber);
			equipment.setqImportNumber(qImportNumber);
			equipment.setqImportBrand(qImportBrand);
			equipment.setqDomesticNumber(qDomesticNumber);
			equipment.setqDomesticBrand(qDomesticBrand);

			String fqjinji = emp.getFqjinji();
			JSONObject fqqjiObject = JSONObject.parseObject(fqjinji);
			String gfqNumber = fqqjiObject.getString("gfqNumber");
			String gfqImportNumber = fqqjiObject.getString("gfqImportNumber");
			String standardfq = fqqjiObject.getString("standardfq");
			String domesticsfq = fqqjiObject.getString("domesticsfq");
			equipment.setGfqNumber(gfqNumber);
			equipment.setGfqImportNumber(gfqImportNumber);
			equipment.setStandardfq(standardfq);
			equipment.setDomesticsfq(domesticsfq);

			String newplaceFqjinji = emp.getNewplaceFqjinji();
			JSONObject newfq = JSONObject.parseObject(newplaceFqjinji);
			String treeYeanfqNumber = newfq.getString("treeYeanfqNumber");
			String daySurNumber = newfq.getString("daySurNumber");
			String commonlyNumber = newfq.getString("commonlyNumber");
			String standbyNumber = newfq.getString("standbyNumber");
			String freeNumber = newfq.getString("freeNumber");
			equipment.setTreeYeanfqNumber(treeYeanfqNumber);
			equipment.setDaySurNumber(daySurNumber);
			equipment.setCommonlyNumber(commonlyNumber);
			equipment.setStandbyNumber(standbyNumber);
			equipment.setFreeNumber(freeNumber);

			String carryQsurgery = emp.getCarryQsurgery();
			JSONObject cq = JSONObject.parseObject(carryQsurgery);
			String treeYeaSurgeryYw = cq.getString("treeYeaSurgeryYw");
			String example = cq.getString("example");
			equipment.setTreeYeaSurgeryYw(treeYeaSurgeryYw);
			equipment.setExample(example);
			String supplyState = emp.getSupplyState();
			equipment.setSupplyState(supplyState);

			String qiangjiqiDisin = emp.getQiangjiqiDisin();
			JSONObject qiDisin = JSONObject.parseObject(qiangjiqiDisin);
			String disinWay = qiDisin.getString("disinWay");
			String liquidType = qiDisin.getString("liquidType");
			String manufacturer = qiDisin.getString("manufacturer");
			String disinTime = qiDisin.getString("disinTime");
			equipment.setDisinWay(disinWay);
			equipment.setLiquidType(liquidType);
			equipment.setManufacturer(manufacturer);
			equipment.setDisinTime(disinTime);
		}
		return equipment;
	}

	/**
	 * 导出word文档
	 * 
	 * @param dataMap
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("exportWord")
	public void excWord(Map<String, Object> dataMap,HttpServletResponse response,HttpServletRequest request,String path,Integer hospitalId) throws RowsExceededException, WriteException, IOException{		
		if(!CommonUtils.isEmpty(hospitalId)){
			//医院基本情况
			Hospital hp = new Hospital();
			hp.setId(hospitalId);
			Hospital hospital = hospitalService.findHospital(hp);
			hospitalIsNull(hospital);
			dataMap.put("hospital", hospital);
			
			//人员情况
			List<StaffDto> staffList = staff(hospitalId);
			dataMap.put("staffList", staffList);
			
			// 设备信息
			EquipmentTto equipment = equipment(hospitalId);	
			equipmentIsNull(equipment);
			dataMap.put("equipment", equipment);

			// 临床应用	
			ClinicalApplicationDto clinicalApplication = clinicalApplication(hospitalId);
			clinicalApplicationIsNull(clinicalApplication);
			dataMap.put("clinicalApplication", clinicalApplication);			
		}
					
        String wordPath=request.getRealPath("/");
		TempltUtil.createWord(dataMap,wordPath);
		
		//path是指欲下载的文件的路径。
		path = wordPath + TempltUtil.QUESTION_DOC;		
		//文件下载
		downFile(response, path);
		
    }

	private void clinicalApplicationIsNull(ClinicalApplicationDto clinicalApplication) {
		if(CommonUtils.isEmpty(clinicalApplication.getFqLocalSurgery())){
			clinicalApplication.setFqLocalSurgery("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getFqLeftSurgery())){
			clinicalApplication.setFqLeftSurgery("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getFqLeftSideSurgery())){
			clinicalApplication.setFqLeftSideSurgery("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getFqRightSideSurgery())){
			clinicalApplication.setFqRightSideSurgery("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getGndgProportion())){
			clinicalApplication.setGndgProportion("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getGzlProportion())){
			clinicalApplication.setGzlProportion("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getKfProportion())){
			clinicalApplication.setKfProportion("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getProportion())){
			clinicalApplication.setProportion("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getMedical())){
			clinicalApplication.setMedical("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getCmDisease())){
			clinicalApplication.setCmDisease("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getLackDoctor())){
			clinicalApplication.setLackDoctor("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getLackEquipment())){
			clinicalApplication.setLackEquipment("　");
		}			
		if(CommonUtils.isEmpty(clinicalApplication.getOtherReasons())){
			clinicalApplication.setOtherReasons("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getReasons())){
			clinicalApplication.setReasons("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getZkfProportion())){
			clinicalApplication.setZkfProportion("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getFqldNumber())){
			clinicalApplication.setFqldNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getDdssNumber())){
			clinicalApplication.setDdssNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getBleedingNumber())){
			clinicalApplication.setBleedingNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getDzgjsNumber())){
			clinicalApplication.setDzgjsNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getOtherNumber())){
			clinicalApplication.setOtherNumber("　");
		}						
		if(CommonUtils.isEmpty(clinicalApplication.getBleedingMl())){
			clinicalApplication.setOperationTime("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getBleedingMl())){
			clinicalApplication.setBleedingMl("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getPortazsNumber())){
			clinicalApplication.setPortazsNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getPortazsTime())){
			clinicalApplication.setPortazsTime("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getTwoSurgeryNumber())){
			clinicalApplication.setTwoSurgeryNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getBfzBleedingNumber())){
			clinicalApplication.setBfzBleedingNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getBfzFistulaNumber())){
			clinicalApplication.setBfzFistulaNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getBfzQkgrNumber())){
			clinicalApplication.setBfzQkgrNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getBfzFqgrNumber())){
			clinicalApplication.setBfzFqgrNumber("　");
		}
		if(CommonUtils.isEmpty(clinicalApplication.getBfzOtherNumber())){
			clinicalApplication.setBfzOtherNumber("　");
		}
	}

	private void equipmentIsNull(EquipmentTto equipment) {
		if(CommonUtils.isEmpty(equipment.getqNumber())){
			equipment.setqNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getqImportNumber())){
			equipment.setqImportNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getqImportBrand())){
			equipment.setqImportBrand("　");
		}
		if(CommonUtils.isEmpty(equipment.getqDomesticNumber())){
			equipment.setqDomesticNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getqDomesticBrand())){
			equipment.setqDomesticBrand("　");
		}
		if(CommonUtils.isEmpty(equipment.getGfqNumber())){
			equipment.setGfqNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getGfqImportNumber())){
			equipment.setGfqImportNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getStandardfq())){
			equipment.setStandardfq("　");
		}
		if(CommonUtils.isEmpty(equipment.getDomesticsfq())){
			equipment.setDomesticsfq("　");
		}
		if(CommonUtils.isEmpty(equipment.getTreeYeanfqNumber())){
			equipment.setTreeYeanfqNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getDaySurNumber())){
			equipment.setDaySurNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getCommonlyNumber())){
			equipment.setCommonlyNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getStandbyNumber())){
			equipment.setStandbyNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getFreeNumber())){
			equipment.setFreeNumber("　");
		}
		if(CommonUtils.isEmpty(equipment.getTreeYeaSurgeryYw())){
			equipment.setTreeYeaSurgeryYw("　");
		}
		if(CommonUtils.isEmpty(equipment.getExample())){
			equipment.setExample("　");
		}
		if(CommonUtils.isEmpty(equipment.getSupplyState())){
			equipment.setSupplyState("　");
		}
		if(CommonUtils.isEmpty(equipment.getDisinWay())){
			equipment.setDisinWay("　");
		}
		if(CommonUtils.isEmpty(equipment.getLiquidType())){
			equipment.setLiquidType("　");
		}
		if(CommonUtils.isEmpty(equipment.getManufacturer())){
			equipment.setManufacturer("　");
		}
		if(CommonUtils.isEmpty(equipment.getDisinTime())){
			equipment.setDisinTime("　");
		}
	}

	private void hospitalIsNull(Hospital hospital) {
		if(CommonUtils.isEmpty(hospital.getHospitalName())){
			hospital.setHospitalName("　");
		}
		if(CommonUtils.isEmpty(hospital.getLegalRepresentative())){
			hospital.setLegalRepresentative("　");
		}
		if(CommonUtils.isEmpty(hospital.getContact())){
			hospital.setContact("　");
		}
		if(CommonUtils.isEmpty(hospital.getHospitalAddress())){
			hospital.setHospitalAddress("　");
		}
		if(CommonUtils.isEmpty(hospital.getTelephoneFax())){
			hospital.setTelephoneFax("　");
		}
		if(CommonUtils.isEmpty(hospital.getKsPosition())){
			hospital.setKsPosition("　");
		}
		if(CommonUtils.isEmpty(hospital.getEmail())){
			hospital.setEmail("　");
		}
		if(CommonUtils.isEmpty(hospital.getContactPhone())){
			hospital.setContactPhone("　");
		}
		if(CommonUtils.isEmpty(hospital.getHospitalLevel())){
			hospital.setHospitalLevel("　");
		}
		if(CommonUtils.isEmpty(hospital.getHospitalNature())){
			hospital.setHospitalNature("　");
		}
		if(CommonUtils.isEmpty(hospital.getBeds())){
			hospital.setBeds("　");
		}
	}
	
	/**
	 * 文件下载
	 * @param response
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unused")
	private static HttpServletResponse downFile(HttpServletResponse response, String path) {
		try {
			// path是指欲下载的文件的路径。          
			File file =  new File(path);
			// 取得文件名。 
			String fileName = null;
	  		fileName = file.getName();
	  	    // 以流的形式下载文件。
	  		InputStream fis = new BufferedInputStream(new FileInputStream(file));
	  		byte[] buffer = new byte[fis.available()];
	  		fis.read(buffer);
		    fis.close();
		    // 清空response  
		    response.reset();
			// 设置response的Header 
		    fileName = URLEncoder.encode(fileName,"UTF-8");
		    if(fileName.length() > 150){
		        //解决IE 6.0 bug   
		    	fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1"); 
		    }
		    response.addHeader("Content-Disposition","attachment;fileName=" + fileName);
		    response.addHeader("Content-Length","" + file.length());
		    OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		    response.setContentType("application/octet-stream");
		    //response.setContentType("application/msword");
		    toClient.write(buffer);
		    toClient.flush();
		    toClient.close();
		    file.delete();
		} catch (Exception e) {
			TempltUtil.log.error("下载文档(WordUtil)出错：【msg："+e.getMessage()+"】 ");
			e.printStackTrace();
		}
		return response;
	}
	
	private List<StaffDto> staff(Integer hospitalId){
		Ks ks = new Ks();
		ks.setHospitalId(hospitalId);
		Ks keshi = ksService.findKs(ks);
		List<StaffDto> staffList = new ArrayList<StaffDto>();
		if (!CommonUtils.isEmpty(keshi)) {			
			String generalSurgery = keshi.getGeneralSurgery();
			JSONObject generalObject = JSONObject.parseObject(generalSurgery);
			String general = generalObject.getString("普外科");
			if (!CommonUtils.isEmpty(general)) {
				StaffDto staff = new StaffDto();
				JSONObject gObject = JSONObject.parseObject(general);
				String physiciansNumber = gObject.getString("physiciansNumber");
				String chiefPhysician = gObject.getString("chiefPhysician");
				String fchiefPhysician = gObject.getString("fchiefPhysician");
				String attendingNumber = gObject.getString("attendingNumber");
				String residentNumber = gObject.getString("residentNumber");
				String drNumber = gObject.getString("drNumber");
				String masterNumber = gObject.getString("masterNumber");
				String bachelorNumber = gObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {					
					staff.setKsName("普外科");
					staff.setPhysiciansNumber(physiciansNumber);
					staff.setChiefPhysician(chiefPhysician);
					staff.setFchiefPhysician(fchiefPhysician);
					staff.setAttendingNumber(attendingNumber);
					staff.setResidentNumber(residentNumber);
					staff.setDrNumber(drNumber);
					staff.setMasterNumber(masterNumber);
					staff.setBachelorNumber(bachelorNumber);
					staffList.add(staff);
				}
			}

			String gallbladderSurgery = generalObject.getString("肝胆外科");
			if (!CommonUtils.isEmpty(gallbladderSurgery)) {
				StaffDto staff1 = new StaffDto();
				JSONObject gallbladderObject = JSONObject.parseObject(gallbladderSurgery);
				String physiciansNumber = gallbladderObject.getString("physiciansNumber");
				String chiefPhysician = gallbladderObject.getString("chiefPhysician");
				String fchiefPhysician = gallbladderObject.getString("fchiefPhysician");
				String attendingNumber = gallbladderObject.getString("attendingNumber");
				String residentNumber = gallbladderObject.getString("residentNumber");
				String drNumber = gallbladderObject.getString("drNumber");
				String masterNumber = gallbladderObject.getString("masterNumber");
				String bachelorNumber = gallbladderObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {

					staff1.setKsName("肝胆外科");
					staff1.setPhysiciansNumber(physiciansNumber);
					staff1.setChiefPhysician(chiefPhysician);
					staff1.setFchiefPhysician(fchiefPhysician);
					staff1.setAttendingNumber(attendingNumber);
					staff1.setResidentNumber(residentNumber);
					staff1.setDrNumber(drNumber);
					staff1.setMasterNumber(masterNumber);
					staff1.setBachelorNumber(bachelorNumber);
					staffList.add(staff1);
				}
			}

			String gastrointesSurgery = generalObject.getString("胃肠外科");
			if (!CommonUtils.isEmpty(gastrointesSurgery)) {
				StaffDto staff2 = new StaffDto();
				JSONObject gastrointesObject = JSONObject.parseObject(gastrointesSurgery);
				String physiciansNumber = gastrointesObject.getString("physiciansNumber");
				String chiefPhysician = gastrointesObject.getString("chiefPhysician");
				String fchiefPhysician = gastrointesObject.getString("fchiefPhysician");
				String attendingNumber = gastrointesObject.getString("attendingNumber");
				String residentNumber = gastrointesObject.getString("residentNumber");
				String drNumber = gastrointesObject.getString("drNumber");
				String masterNumber = gastrointesObject.getString("masterNumber");
				String bachelorNumber = gastrointesObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					staff2.setKsName("胃肠外科");
					staff2.setPhysiciansNumber(physiciansNumber);
					staff2.setChiefPhysician(chiefPhysician);
					staff2.setFchiefPhysician(fchiefPhysician);
					staff2.setAttendingNumber(attendingNumber);
					staff2.setResidentNumber(residentNumber);
					staff2.setDrNumber(drNumber);
					staff2.setMasterNumber(masterNumber);
					staff2.setBachelorNumber(bachelorNumber);
					staffList.add(staff2);
				}
			}

			String herniaSurgery = generalObject.getString("疝外科");
			if (!CommonUtils.isEmpty(herniaSurgery)) {
				StaffDto staff3 = new StaffDto();
				JSONObject herniaObject = JSONObject.parseObject(herniaSurgery);
				String physiciansNumber = herniaObject.getString("physiciansNumber");
				String chiefPhysician = herniaObject.getString("chiefPhysician");
				String fchiefPhysician = herniaObject.getString("fchiefPhysician");
				String attendingNumber = herniaObject.getString("attendingNumber");
				String residentNumber = herniaObject.getString("residentNumber");
				String drNumber = herniaObject.getString("drNumber");
				String masterNumber = herniaObject.getString("masterNumber");
				String bachelorNumber = herniaObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					staff3.setKsName("疝外科");
					staff3.setPhysiciansNumber(physiciansNumber);
					staff3.setChiefPhysician(chiefPhysician);
					staff3.setFchiefPhysician(fchiefPhysician);
					staff3.setAttendingNumber(attendingNumber);
					staff3.setResidentNumber(residentNumber);
					staff3.setDrNumber(drNumber);
					staff3.setMasterNumber(masterNumber);
					staff3.setBachelorNumber(bachelorNumber);
					staffList.add(staff3);
				}
			}

			String breastSurgery = generalObject.getString("甲乳外科");
			if (!CommonUtils.isEmpty(breastSurgery)) {
				StaffDto staff4 = new StaffDto();
				JSONObject breastObject = JSONObject.parseObject(breastSurgery);
				String physiciansNumber = breastObject.getString("physiciansNumber");
				String chiefPhysician = breastObject.getString("chiefPhysician");
				String fchiefPhysician = breastObject.getString("fchiefPhysician");
				String attendingNumber = breastObject.getString("attendingNumber");
				String residentNumber = breastObject.getString("residentNumber");
				String drNumber = breastObject.getString("drNumber");
				String masterNumber = breastObject.getString("masterNumber");
				String bachelorNumber = breastObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					staff4.setKsName("甲乳外科");
					staff4.setPhysiciansNumber(physiciansNumber);
					staff4.setChiefPhysician(chiefPhysician);
					staff4.setFchiefPhysician(fchiefPhysician);
					staff4.setAttendingNumber(attendingNumber);
					staff4.setResidentNumber(residentNumber);
					staff4.setDrNumber(drNumber);
					staff4.setMasterNumber(masterNumber);
					staff4.setBachelorNumber(bachelorNumber);
					staffList.add(staff4);
				}
			}

			String vascularSurgery = generalObject.getString("血管外科");
			if (!CommonUtils.isEmpty(vascularSurgery)) {
				StaffDto staff5 = new StaffDto();
				JSONObject vascularObject = JSONObject.parseObject(vascularSurgery);
				String physiciansNumber = vascularObject.getString("physiciansNumber");
				String chiefPhysician = vascularObject.getString("chiefPhysician");
				String fchiefPhysician = vascularObject.getString("fchiefPhysician");
				String attendingNumber = vascularObject.getString("attendingNumber");
				String residentNumber = vascularObject.getString("residentNumber");
				String drNumber = vascularObject.getString("drNumber");
				String masterNumber = vascularObject.getString("masterNumber");
				String bachelorNumber = vascularObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					staff5.setKsName("血管外科");
					staff5.setPhysiciansNumber(physiciansNumber);
					staff5.setChiefPhysician(chiefPhysician);
					staff5.setFchiefPhysician(fchiefPhysician);
					staff5.setAttendingNumber(attendingNumber);
					staff5.setResidentNumber(residentNumber);
					staff5.setDrNumber(drNumber);
					staff5.setMasterNumber(masterNumber);
					staff5.setBachelorNumber(bachelorNumber);
					staffList.add(staff5);
				}
			}

			// 胸外科
			String thoracicSurgery = keshi.getThoracicSurgery();
			JSONObject thoracicObject = JSONObject.parseObject(thoracicSurgery);
			String thoracic = thoracicObject.getString("胸外科");
			if (!CommonUtils.isEmpty(thoracic)) {
				StaffDto thoracicStaff = new StaffDto();
				JSONObject tObject = JSONObject.parseObject(thoracic);
				String physiciansNumber = tObject.getString("physiciansNumber");
				String chiefPhysician = tObject.getString("chiefPhysician");
				String fchiefPhysician = tObject.getString("fchiefPhysician");
				String attendingNumber = tObject.getString("attendingNumber");
				String residentNumber = tObject.getString("residentNumber");
				String drNumber = tObject.getString("drNumber");
				String masterNumber = tObject.getString("masterNumber");
				String bachelorNumber = tObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					thoracicStaff.setKsName("胸外科");
					thoracicStaff.setPhysiciansNumber(physiciansNumber);
					thoracicStaff.setChiefPhysician(chiefPhysician);
					thoracicStaff.setFchiefPhysician(fchiefPhysician);
					thoracicStaff.setAttendingNumber(attendingNumber);
					thoracicStaff.setResidentNumber(residentNumber);
					thoracicStaff.setDrNumber(drNumber);
					thoracicStaff.setMasterNumber(masterNumber);
					thoracicStaff.setBachelorNumber(bachelorNumber);
					staffList.add(thoracicStaff);
				}
			}

			String ptThoracicSurgery = thoracicObject.getString("普胸外科");
			if (!CommonUtils.isEmpty(ptThoracicSurgery)) {
				StaffDto ptStaff = new StaffDto();
				JSONObject ptObject = JSONObject.parseObject(ptThoracicSurgery);
				String physiciansNumber = ptObject.getString("physiciansNumber");
				String chiefPhysician = ptObject.getString("chiefPhysician");
				String fchiefPhysician = ptObject.getString("fchiefPhysician");
				String attendingNumber = ptObject.getString("attendingNumber");
				String residentNumber = ptObject.getString("residentNumber");
				String drNumber = ptObject.getString("drNumber");
				String masterNumber = ptObject.getString("masterNumber");
				String bachelorNumber = ptObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					ptStaff.setKsName("普胸外科");
					ptStaff.setPhysiciansNumber(physiciansNumber);
					ptStaff.setChiefPhysician(chiefPhysician);
					ptStaff.setFchiefPhysician(fchiefPhysician);
					ptStaff.setAttendingNumber(attendingNumber);
					ptStaff.setResidentNumber(residentNumber);
					ptStaff.setDrNumber(drNumber);
					ptStaff.setMasterNumber(masterNumber);
					ptStaff.setBachelorNumber(bachelorNumber);
					staffList.add(ptStaff);
				}
			}

			String cardiacSurgery = thoracicObject.getString("心脏外科");
			if (!CommonUtils.isEmpty(cardiacSurgery)) {
				StaffDto cardiacStaff = new StaffDto();
				JSONObject cardiacObject = JSONObject.parseObject(cardiacSurgery);
				String physiciansNumber = cardiacObject.getString("physiciansNumber");
				String chiefPhysician = cardiacObject.getString("chiefPhysician");
				String fchiefPhysician = cardiacObject.getString("fchiefPhysician");
				String attendingNumber = cardiacObject.getString("attendingNumber");
				String residentNumber = cardiacObject.getString("residentNumber");
				String drNumber = cardiacObject.getString("drNumber");
				String masterNumber = cardiacObject.getString("masterNumber");
				String bachelorNumber = cardiacObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					cardiacStaff.setKsName("心脏外科");
					cardiacStaff.setPhysiciansNumber(physiciansNumber);
					cardiacStaff.setChiefPhysician(chiefPhysician);
					cardiacStaff.setFchiefPhysician(fchiefPhysician);
					cardiacStaff.setAttendingNumber(attendingNumber);
					cardiacStaff.setResidentNumber(residentNumber);
					cardiacStaff.setDrNumber(drNumber);
					cardiacStaff.setMasterNumber(masterNumber);
					cardiacStaff.setBachelorNumber(bachelorNumber);
					staffList.add(cardiacStaff);
				}
			}

			// 泌尿外科
			String urology = keshi.getUrology();
			JSONObject urologyObject = JSONObject.parseObject(urology);
			String ug = urologyObject.getString("泌尿外科");
			if (!CommonUtils.isEmpty(ug)) {
				StaffDto ugStaff = new StaffDto();
				JSONObject ugObject = JSONObject.parseObject(ug);
				String physiciansNumber = ugObject.getString("physiciansNumber");
				String chiefPhysician = ugObject.getString("chiefPhysician");
				String fchiefPhysician = ugObject.getString("fchiefPhysician");
				String attendingNumber = ugObject.getString("attendingNumber");
				String residentNumber = ugObject.getString("residentNumber");
				String drNumber = ugObject.getString("drNumber");
				String masterNumber = ugObject.getString("masterNumber");
				String bachelorNumber = ugObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					ugStaff.setKsName("泌尿外科");
					ugStaff.setPhysiciansNumber(physiciansNumber);
					ugStaff.setChiefPhysician(chiefPhysician);
					ugStaff.setFchiefPhysician(fchiefPhysician);
					ugStaff.setAttendingNumber(attendingNumber);
					ugStaff.setResidentNumber(residentNumber);
					ugStaff.setDrNumber(drNumber);
					ugStaff.setMasterNumber(masterNumber);
					ugStaff.setBachelorNumber(bachelorNumber);
					staffList.add(ugStaff);
				}
			}

			String urologicalOncology = urologyObject.getString("泌尿肿瘤科");
			if (!CommonUtils.isEmpty(urologicalOncology)) {
				StaffDto urologStaff = new StaffDto();
				JSONObject urologObject = JSONObject.parseObject(urologicalOncology);
				String physiciansNumber = urologObject.getString("physiciansNumber");
				String chiefPhysician = urologObject.getString("chiefPhysician");
				String fchiefPhysician = urologObject.getString("fchiefPhysician");
				String attendingNumber = urologObject.getString("attendingNumber");
				String residentNumber = urologObject.getString("residentNumber");
				String drNumber = urologObject.getString("drNumber");
				String masterNumber = urologObject.getString("masterNumber");
				String bachelorNumber = urologObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					urologStaff.setKsName("泌尿肿瘤科");
					urologStaff.setPhysiciansNumber(physiciansNumber);
					urologStaff.setChiefPhysician(chiefPhysician);
					urologStaff.setFchiefPhysician(fchiefPhysician);
					urologStaff.setAttendingNumber(attendingNumber);
					urologStaff.setResidentNumber(residentNumber);
					urologStaff.setDrNumber(drNumber);
					urologStaff.setMasterNumber(masterNumber);
					urologStaff.setBachelorNumber(bachelorNumber);
					staffList.add(urologStaff);
				}
			}

			String stonesBranch = urologyObject.getString("尿路结石科");
			if (!CommonUtils.isEmpty(stonesBranch)) {
				StaffDto stonesStaff = new StaffDto();
				JSONObject stonesObject = JSONObject.parseObject(stonesBranch);
				String physiciansNumber = stonesObject.getString("physiciansNumber");
				String chiefPhysician = stonesObject.getString("chiefPhysician");
				String fchiefPhysician = stonesObject.getString("fchiefPhysician");
				String attendingNumber = stonesObject.getString("attendingNumber");
				String residentNumber = stonesObject.getString("residentNumber");
				String drNumber = stonesObject.getString("drNumber");
				String masterNumber = stonesObject.getString("masterNumber");
				String bachelorNumber = stonesObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					stonesStaff.setKsName("尿路结石科");
					stonesStaff.setPhysiciansNumber(physiciansNumber);
					stonesStaff.setChiefPhysician(chiefPhysician);
					stonesStaff.setFchiefPhysician(fchiefPhysician);
					stonesStaff.setAttendingNumber(attendingNumber);
					stonesStaff.setResidentNumber(residentNumber);
					stonesStaff.setDrNumber(drNumber);
					stonesStaff.setMasterNumber(masterNumber);
					stonesStaff.setBachelorNumber(bachelorNumber);
					staffList.add(stonesStaff);
				}
			}

			// 妇产科
			String womenObstetrics = keshi.getWomenObstetrics();
			JSONObject womenObject = JSONObject.parseObject(womenObstetrics);
			String women = womenObject.getString("妇产科");
			if (!CommonUtils.isEmpty(women)) {
				StaffDto womenStaff = new StaffDto();
				JSONObject wObject = JSONObject.parseObject(women);
				String physiciansNumber = wObject.getString("physiciansNumber");
				String chiefPhysician = wObject.getString("chiefPhysician");
				String fchiefPhysician = wObject.getString("fchiefPhysician");
				String attendingNumber = wObject.getString("attendingNumber");
				String residentNumber = wObject.getString("residentNumber");
				String drNumber = wObject.getString("drNumber");
				String masterNumber = wObject.getString("masterNumber");
				String bachelorNumber = wObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					womenStaff.setKsName("妇产科");
					womenStaff.setPhysiciansNumber(physiciansNumber);
					womenStaff.setChiefPhysician(chiefPhysician);
					womenStaff.setFchiefPhysician(fchiefPhysician);
					womenStaff.setAttendingNumber(attendingNumber);
					womenStaff.setResidentNumber(residentNumber);
					womenStaff.setDrNumber(drNumber);
					womenStaff.setMasterNumber(masterNumber);
					womenStaff.setBachelorNumber(bachelorNumber);
					staffList.add(womenStaff);
				}
			}

			String gynecologicOncology = womenObject.getString("妇科肿瘤科");
			if (!CommonUtils.isEmpty(gynecologicOncology)) {
				StaffDto gynecologStaff = new StaffDto();
				JSONObject gynecologObject = JSONObject.parseObject(gynecologicOncology);
				String physiciansNumber = gynecologObject.getString("physiciansNumber");
				String chiefPhysician = gynecologObject.getString("chiefPhysician");
				String fchiefPhysician = gynecologObject.getString("fchiefPhysician");
				String attendingNumber = gynecologObject.getString("attendingNumber");
				String residentNumber = gynecologObject.getString("residentNumber");
				String drNumber = gynecologObject.getString("drNumber");
				String masterNumber = gynecologObject.getString("masterNumber");
				String bachelorNumber = gynecologObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					gynecologStaff.setKsName("妇科肿瘤科");
					gynecologStaff.setPhysiciansNumber(physiciansNumber);
					gynecologStaff.setChiefPhysician(chiefPhysician);
					gynecologStaff.setFchiefPhysician(fchiefPhysician);
					gynecologStaff.setAttendingNumber(attendingNumber);
					gynecologStaff.setResidentNumber(residentNumber);
					gynecologStaff.setDrNumber(drNumber);
					gynecologStaff.setMasterNumber(masterNumber);
					gynecologStaff.setBachelorNumber(bachelorNumber);
					staffList.add(gynecologStaff);
				}
			}

			String ptGynaecology = womenObject.getString("普通妇科");
			if (!CommonUtils.isEmpty(ptGynaecology)) {
				StaffDto pgStaff = new StaffDto();
				JSONObject pgObject = JSONObject.parseObject(ptGynaecology);
				String physiciansNumber = pgObject.getString("physiciansNumber");
				String chiefPhysician = pgObject.getString("chiefPhysician");
				String fchiefPhysician = pgObject.getString("fchiefPhysician");
				String attendingNumber = pgObject.getString("attendingNumber");
				String residentNumber = pgObject.getString("residentNumber");
				String drNumber = pgObject.getString("drNumber");
				String masterNumber = pgObject.getString("masterNumber");
				String bachelorNumber = pgObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					pgStaff.setKsName("普通妇科	");
					pgStaff.setPhysiciansNumber(physiciansNumber);
					pgStaff.setChiefPhysician(chiefPhysician);
					pgStaff.setFchiefPhysician(fchiefPhysician);
					pgStaff.setAttendingNumber(attendingNumber);
					pgStaff.setResidentNumber(residentNumber);
					pgStaff.setDrNumber(drNumber);
					pgStaff.setMasterNumber(masterNumber);
					pgStaff.setBachelorNumber(bachelorNumber);
					staffList.add(pgStaff);
				}
			}

			// 骨科
			String orthopaedic = keshi.getOrthopaedic();
			JSONObject orthopaedicObject = JSONObject.parseObject(orthopaedic);
			String gk = orthopaedicObject.getString("骨科");
			if (!CommonUtils.isEmpty(gk)) {
				StaffDto gkStaff = new StaffDto();
				JSONObject gkObject = JSONObject.parseObject(gk);
				String physiciansNumber = gkObject.getString("physiciansNumber");
				String chiefPhysician = gkObject.getString("chiefPhysician");
				String fchiefPhysician = gkObject.getString("fchiefPhysician");
				String attendingNumber = gkObject.getString("attendingNumber");
				String residentNumber = gkObject.getString("residentNumber");
				String drNumber = gkObject.getString("drNumber");
				String masterNumber = gkObject.getString("masterNumber");
				String bachelorNumber = gkObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					gkStaff.setKsName("骨科");
					gkStaff.setPhysiciansNumber(physiciansNumber);
					gkStaff.setChiefPhysician(chiefPhysician);
					gkStaff.setFchiefPhysician(fchiefPhysician);
					gkStaff.setAttendingNumber(attendingNumber);
					gkStaff.setResidentNumber(residentNumber);
					gkStaff.setDrNumber(drNumber);
					gkStaff.setMasterNumber(masterNumber);
					gkStaff.setBachelorNumber(bachelorNumber);
					staffList.add(gkStaff);
				}
			}

			String spineSurgery = orthopaedicObject.getString("脊柱外科");
			if (!CommonUtils.isEmpty(spineSurgery)) {
				StaffDto gzStaff = new StaffDto();
				JSONObject gzObject = JSONObject.parseObject(spineSurgery);
				String physiciansNumber = gzObject.getString("physiciansNumber");
				String chiefPhysician = gzObject.getString("chiefPhysician");
				String fchiefPhysician = gzObject.getString("fchiefPhysician");
				String attendingNumber = gzObject.getString("attendingNumber");
				String residentNumber = gzObject.getString("residentNumber");
				String drNumber = gzObject.getString("drNumber");
				String masterNumber = gzObject.getString("masterNumber");
				String bachelorNumber = gzObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					gzStaff.setKsName("脊柱外科");
					gzStaff.setPhysiciansNumber(physiciansNumber);
					gzStaff.setChiefPhysician(chiefPhysician);
					gzStaff.setFchiefPhysician(fchiefPhysician);
					gzStaff.setAttendingNumber(attendingNumber);
					gzStaff.setResidentNumber(residentNumber);
					gzStaff.setDrNumber(drNumber);
					gzStaff.setMasterNumber(masterNumber);
					gzStaff.setBachelorNumber(bachelorNumber);
					staffList.add(gzStaff);
				}
			}

			String jointSurgery = orthopaedicObject.getString("骨关节外科");
			if (!CommonUtils.isEmpty(jointSurgery)) {
				StaffDto jointStaff = new StaffDto();
				JSONObject jointObject = JSONObject.parseObject(jointSurgery);
				String physiciansNumber = jointObject.getString("physiciansNumber");
				String chiefPhysician = jointObject.getString("chiefPhysician");
				String fchiefPhysician = jointObject.getString("fchiefPhysician");
				String attendingNumber = jointObject.getString("attendingNumber");
				String residentNumber = jointObject.getString("residentNumber");
				String drNumber = jointObject.getString("drNumber");
				String masterNumber = jointObject.getString("masterNumber");
				String bachelorNumber = jointObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					jointStaff.setKsName("骨关节外科");
					jointStaff.setPhysiciansNumber(physiciansNumber);
					jointStaff.setChiefPhysician(chiefPhysician);
					jointStaff.setFchiefPhysician(fchiefPhysician);
					jointStaff.setAttendingNumber(attendingNumber);
					jointStaff.setResidentNumber(residentNumber);
					jointStaff.setDrNumber(drNumber);
					jointStaff.setMasterNumber(masterNumber);
					jointStaff.setBachelorNumber(bachelorNumber);
					staffList.add(jointStaff);
				}
			}

			// 放射介入
			String radioactive = keshi.getRadioactive();
			JSONObject fsObject = JSONObject.parseObject(radioactive);
			String fsjrk = fsObject.getString("放射介入科");
			if (!CommonUtils.isEmpty(fsjrk)) {
				StaffDto fsjrkStaff = new StaffDto();
				JSONObject fsjrkObject = JSONObject.parseObject(fsjrk);
				String physiciansNumber = fsjrkObject.getString("physiciansNumber");
				String chiefPhysician = fsjrkObject.getString("chiefPhysician");
				String fchiefPhysician = fsjrkObject.getString("fchiefPhysician");
				String attendingNumber = fsjrkObject.getString("attendingNumber");
				String residentNumber = fsjrkObject.getString("residentNumber");
				String drNumber = fsjrkObject.getString("drNumber");
				String masterNumber = fsjrkObject.getString("masterNumber");
				String bachelorNumber = fsjrkObject.getString("bachelorNumber");
				if (!CommonUtils.isEmpty(physiciansNumber) || !CommonUtils.isEmpty(chiefPhysician)
						|| !CommonUtils.isEmpty(fchiefPhysician) || !CommonUtils.isEmpty(attendingNumber)
						|| !CommonUtils.isEmpty(residentNumber) || !CommonUtils.isEmpty(drNumber)
						|| !CommonUtils.isEmpty(masterNumber) || !CommonUtils.isEmpty(bachelorNumber)) {
					fsjrkStaff.setKsName("放射介入科");
					fsjrkStaff.setPhysiciansNumber(physiciansNumber);
					fsjrkStaff.setChiefPhysician(chiefPhysician);
					fsjrkStaff.setFchiefPhysician(fchiefPhysician);
					fsjrkStaff.setAttendingNumber(attendingNumber);
					fsjrkStaff.setResidentNumber(residentNumber);
					fsjrkStaff.setDrNumber(drNumber);
					fsjrkStaff.setMasterNumber(masterNumber);
					fsjrkStaff.setBachelorNumber(bachelorNumber);
					staffList.add(fsjrkStaff);
				}
			}
	  }
		return staffList;
    }  
}
