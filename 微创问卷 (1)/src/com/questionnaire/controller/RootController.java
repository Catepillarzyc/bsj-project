package com.questionnaire.controller;


import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.questionnaire.pojo.dto.KsDto;
import com.questionnaire.pojo.dto.KsStaffDto;
import com.questionnaire.pojo.dto.StaffDto;
import com.questionnaire.service.ClinicalApplicationService;
import com.questionnaire.service.EquipmentService;
import com.questionnaire.service.HospitalService;
import com.questionnaire.service.KsService;
import com.questionnaire.service.UserService;
import com.questionnaire.util.CommonUtils;

@Controller
public class RootController {

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

	private Hospital hospital;
	private KsDto ksDto;
	private KsStaffDto ksStaffDto;
	private EquipmentTto equipmentDto;
	private ClinicalApplicationDto clinicalApplicationDto;

	// 介绍页
	@RequestMapping("introduce")
	public String introduce(Map<String, Object> out) {
		
		return "introduce";
	}
	
	
	 
	// 首页
	@RequestMapping("index")
	public String index(Map<String, Object> out, HttpServletRequest request) {
		Consumer user = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		return "index";
	}
	
	@RequestMapping("addData")
	public String addData(Map<String, Object> out,Hospital hosp,KsDto kDto, 
			KsStaffDto ksStaff,EquipmentTto epDto,ClinicalApplicationDto caDto) {
		JSONObject json = new JSONObject();
		saveData(hosp, kDto, ksStaff, epDto, caDto);
		out.put("json", json);
		return "json";
	}
	
	//首页提交问卷
	@RequestMapping("indexSubmit")
	public String indexSubmit(Map<String, Object> out,Hospital hosp,KsDto kDto, 
			KsStaffDto ksStaff,EquipmentTto epDto,ClinicalApplicationDto caDto) {
		saveData(hosp, kDto, ksStaff, epDto, caDto);
		JSONObject json = new JSONObject();	
		submit(hospitalService,equipmentService,ksService, clinicalApplicationService,
				json, hospital,ksDto,ksStaffDto,equipmentDto,clinicalApplicationDto);		
		ksDto = null;
		hospital = null;			
		ksStaffDto = null;
		equipmentDto = null;
		clinicalApplicationDto = null;
		out.put("json", json);		
		return "json";
	}

	// 医院情况
	@RequestMapping("hospital")
	public String hospital(Map<String, Object> out) {
		
		return "hospital";
	}
    
	//暂存医院信息
	@RequestMapping("saveHospital")
	public String deletePatients(Map<String, Object> out, Hospital hosp,Integer flag) {
		JSONObject json = new JSONObject();
		if (!CommonUtils.isEmpty(hosp)) {
			hospital = hosp;
		}
		out.put("json", json);		
		return "json";
	}

	// 科室情况
	@RequestMapping("ksStaff")
	public String ksStaff(Map<String, Object> out) {
					
		return "ksStaff";
	}
    
	//暂存科室医生情况
	@RequestMapping("saveKsStaff")
	public String saveKsStaff(Map<String, Object> out, KsDto ks, KsStaffDto ksStaff,Integer flag) {
		JSONObject json = new JSONObject();
		if (!CommonUtils.isEmpty(ksStaff) && !CommonUtils.isEmpty(ks)) {
			ksDto = ks;
			ksStaffDto = ksStaff;
		}
		out.put("json", json);
		return "json";
	}
 
	// 设备情况
	@RequestMapping("equipment")
	public String equipment(Map<String, Object> out) {
		
		return "equipment";
	}

	// 保存填写的设备信息
	@RequestMapping("saveEquipment")
	public String saveEquipment(Map<String, Object> out, EquipmentTto equipment,Integer flag) {
		JSONObject json = new JSONObject();
		if (!CommonUtils.isEmpty(equipment)) {
			equipmentDto = equipment;
		}
		out.put("json", json);
		return "json";
	}

	// 临床应用
	@RequestMapping("clinicalApplication")
	public String clinicalApplication(Map<String, Object> out) {
		
		return "clinicalApplication";
	}
	
	//不为空就将数据保存
	private void saveData(Hospital hosp, KsDto kDto, KsStaffDto ksStaff, EquipmentTto epDto,
			ClinicalApplicationDto caDto) {
		if(!CommonUtils.isEmpty(hosp)){
			if(!CommonUtils.isEmpty(hosp.getHospitalName())||!CommonUtils.isEmpty(hosp.getLegalRepresentative())||!CommonUtils.isEmpty(hosp.getContact())||!CommonUtils.isEmpty(hosp.getHospitalAddress())||!CommonUtils.isEmpty(hosp.getTelephoneFax())||!CommonUtils.isEmpty(hosp.getKsPosition())||!CommonUtils.isEmpty(hosp.getEmail())||!CommonUtils.isEmpty(hosp.getContactPhone())||!CommonUtils.isEmpty(hosp.getHospitalLevel())||!CommonUtils.isEmpty(hosp.getHospitalNature())||!CommonUtils.isEmpty(hosp.getBeds())){				
				hospital = hosp;
			}
		}
		if(!CommonUtils.isEmpty(kDto)){
		    if(!CommonUtils.isEmpty(kDto.getGeneralSurgery())||!CommonUtils.isEmpty(kDto.getGallbladderSurgery())||!CommonUtils.isEmpty(kDto.getGastrointesSurgery())||!CommonUtils.isEmpty(kDto.getHerniaSurgery())||!CommonUtils.isEmpty(kDto.getBreastSurgery())||!CommonUtils.isEmpty(kDto.getVascularSurgery())||!CommonUtils.isEmpty(kDto.getThoracicSurgery())||!CommonUtils.isEmpty(kDto.getPtThoracicSurgery())||!CommonUtils.isEmpty(kDto.getCardiacSurgery())||!CommonUtils.isEmpty(kDto.getUrology())||!CommonUtils.isEmpty(kDto.getUrologicalOncology())||!CommonUtils.isEmpty(kDto.getStonesBranch())||!CommonUtils.isEmpty(kDto.getWomenObstetrics())||!CommonUtils.isEmpty(kDto.getGynecologicOncology())||!CommonUtils.isEmpty(kDto.getPtGynaecology())||!CommonUtils.isEmpty(kDto.getOrthopaedic())||!CommonUtils.isEmpty(kDto.getSpineSurgery())||!CommonUtils.isEmpty(kDto.getJointSurgery())||!CommonUtils.isEmpty(kDto.getRadioactive())){
			    ksDto = kDto;
			}	
		}
		if(!CommonUtils.isEmpty(ksStaff)){
			if(ksStaff.getAttendingNumber()!=null&&!CommonUtils.isEmpty(ksStaff.getAttendingNumber()[0])||ksStaff.getBachelorNumber()!=null&&!CommonUtils.isEmpty(ksStaff.getBachelorNumber()[0])||ksStaff.getChiefPhysician()!=null&&!CommonUtils.isEmpty(ksStaff.getChiefPhysician()[0])||ksStaff.getDrNumber()!=null&&!CommonUtils.isEmpty(ksStaff.getDrNumber()[0])||ksStaff.getFchiefPhysician()!=null&&!CommonUtils.isEmpty(ksStaff.getFchiefPhysician()[0])||ksStaff.getKsName()!=null&&!CommonUtils.isEmpty(ksStaff.getKsName()[0])||ksStaff.getPhysiciansNumber()!=null&&!CommonUtils.isEmpty(ksStaff.getPhysiciansNumber()[0])||ksStaff.getResidentNumber()!=null&&!CommonUtils.isEmpty(ksStaff.getResidentNumber()[0])||ksStaff.getMasterNumber()!=null&&!CommonUtils.isEmpty(ksStaff.getMasterNumber()[0])){
				ksStaffDto = ksStaff;	
			}
		}
		if(!CommonUtils.isEmpty(epDto)){
			if(!CommonUtils.isEmpty(epDto.getqNumber())||!CommonUtils.isEmpty(epDto.getqImportNumber())||!CommonUtils.isEmpty(epDto.getqImportBrand())||!CommonUtils.isEmpty(epDto.getqDomesticNumber())||!CommonUtils.isEmpty(epDto.getqDomesticBrand())||!CommonUtils.isEmpty(epDto.getGfqNumber())||!CommonUtils.isEmpty(epDto.getGfqImportNumber())||!CommonUtils.isEmpty(epDto.getStandardfq())||!CommonUtils.isEmpty(epDto.getDomesticsfq())||!CommonUtils.isEmpty(epDto.getTreeYeanfqNumber())||!CommonUtils.isEmpty(epDto.getDaySurNumber())||!CommonUtils.isEmpty(epDto.getCommonlyNumber())||!CommonUtils.isEmpty(epDto.getStandbyNumber())||!CommonUtils.isEmpty(epDto.getFreeNumber())||!CommonUtils.isEmpty(epDto.getTreeYeaSurgeryYw())||!CommonUtils.isEmpty(epDto.getExample())||!CommonUtils.isEmpty(epDto.getSupplyState())||!CommonUtils.isEmpty(epDto.getDisinWay())||!CommonUtils.isEmpty(epDto.getLiquidType())||!CommonUtils.isEmpty(epDto.getManufacturer())||!CommonUtils.isEmpty(epDto.getDisinTime())){
				equipmentDto = epDto;		
			}
		}
		if(!CommonUtils.isEmpty(caDto)){			
			if(!CommonUtils.isEmpty(caDto.getGndgProportion())||!CommonUtils.isEmpty(caDto.getGzlProportion())||!CommonUtils.isEmpty(caDto.getKfProportion())||!CommonUtils.isEmpty(caDto.getProportion())||!CommonUtils.isEmpty(caDto.getZkfProportion())||!CommonUtils.isEmpty(caDto.getFqldNumber())||!CommonUtils.isEmpty(caDto.getDdssNumber())||!CommonUtils.isEmpty(caDto.getBleedingNumber())||!CommonUtils.isEmpty(caDto.getDzgjsNumber())||!CommonUtils.isEmpty(caDto.getOtherNumber())||!CommonUtils.isEmpty(caDto.getOperationTime())||!CommonUtils.isEmpty(caDto.getBleedingMl())||	!CommonUtils.isEmpty(caDto.getPortazsNumber())||!CommonUtils.isEmpty(caDto.getPortazsTime())||!CommonUtils.isEmpty(caDto.getTwoSurgeryNumber())||!CommonUtils.isEmpty(caDto.getBfzBleedingNumber())||!CommonUtils.isEmpty(caDto.getBfzFistulaNumber())||!CommonUtils.isEmpty(caDto.getBfzQkgrNumber())||!CommonUtils.isEmpty(caDto.getBfzFqgrNumber())||!CommonUtils.isEmpty(caDto.getBfzOtherNumber())||!CommonUtils.isEmpty(caDto.getMedical())||!CommonUtils.isEmpty(caDto.getCmDisease())||!CommonUtils.isEmpty(caDto.getLackDoctor())||!CommonUtils.isEmpty(caDto.getLackEquipment())||!CommonUtils.isEmpty(caDto.getOtherReasons())||!CommonUtils.isEmpty(caDto.getFqLocalSurgery())||!CommonUtils.isEmpty(caDto.getFqLeftSurgery())||!CommonUtils.isEmpty(caDto.getFqLeftSideSurgery())||!CommonUtils.isEmpty(caDto.getFqRightSideSurgery())){
				clinicalApplicationDto = caDto;
			}
		}
	}

	// 提交
	@RequestMapping("submit")
	public String submit(Map<String, Object> out,ClinicalApplicationDto clinicalApplicationDto) {	
		JSONObject json = new JSONObject();			
		submit(hospitalService,equipmentService,ksService, clinicalApplicationService,
				json, hospital,ksDto,ksStaffDto,equipmentDto,clinicalApplicationDto);		
		out.put("json", json);
		ksDto = null;
		hospital = null;			
		ksStaffDto = null;
		equipmentDto = null;
		clinicalApplicationDto = null;
		return "json";
	}
	
	@SuppressWarnings("unused")
	public static void submit(HospitalService hospitalService,EquipmentService equipmentService,KsService ksService,ClinicalApplicationService clinicalApplicationService,JSONObject json,
			Hospital hospital,KsDto ksDto,KsStaffDto ksStaffDto,EquipmentTto equipmentDto,ClinicalApplicationDto clinicalApplicationDto) {
		Integer flag = -1;
		if(!CommonUtils.isEmpty(hospital)){
			if(!CommonUtils.isEmpty(hospital.getHospitalName())){
				hospitalService.addHospital(hospital);
				if(!CommonUtils.isEmpty(ksDto) && !CommonUtils.isEmpty(ksStaffDto)){
					JSONObject general = new JSONObject();	
					String[] ksName = ksStaffDto.getKsName();
					Ks ks = new Ks();
					String physiciansNumber =null;
					String chiefPhysician = null;
					String fchiefPhysician =null;
					String attendingNumber =null;
					String residentNumber =null;
					String drNumber =null;
					String masterNumber =null;
					String bachelorNumber =null;
					for(int i = 0;i<ksName.length;i++){				
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getGeneralSurgery() != null && ksDto.getGeneralSurgery().equals(name)){
							StaffDto staff = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff.setPhysiciansNumber(physiciansNumber);
							staff.setChiefPhysician(fchiefPhysician);
							staff.setFchiefPhysician(fchiefPhysician);
							staff.setAttendingNumber(attendingNumber);
							staff.setResidentNumber(residentNumber);
							staff.setDrNumber(drNumber);
							staff.setMasterNumber(masterNumber);
							staff.setBachelorNumber(bachelorNumber);
							general.put(ksDto.getGeneralSurgery(),staff);				
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						   
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getGallbladderSurgery() != null && ksDto.getGallbladderSurgery().equals(name)){								
							StaffDto staff1 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");							
							staff1.setPhysiciansNumber(physiciansNumber);
							staff1.setChiefPhysician(fchiefPhysician);
							staff1.setFchiefPhysician(fchiefPhysician);
							staff1.setAttendingNumber(attendingNumber);
							staff1.setResidentNumber(residentNumber);
							staff1.setDrNumber(drNumber);
							staff1.setMasterNumber(masterNumber);
							staff1.setBachelorNumber(bachelorNumber);
							general.put(ksDto.getGallbladderSurgery(),staff1);	
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						   
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getGastrointesSurgery() != null && ksDto.getGastrointesSurgery().equals(name)){
							StaffDto staff2 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");					
							staff2.setPhysiciansNumber(physiciansNumber);
							staff2.setChiefPhysician(fchiefPhysician);
							staff2.setFchiefPhysician(fchiefPhysician);
							staff2.setAttendingNumber(attendingNumber);
							staff2.setResidentNumber(residentNumber);
							staff2.setDrNumber(drNumber);
							staff2.setMasterNumber(masterNumber);
							staff2.setBachelorNumber(bachelorNumber);
							general.put(ksDto.getGastrointesSurgery(),staff2);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						   
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getHerniaSurgery() != null && ksDto.getHerniaSurgery().equals(name)){
							StaffDto staff3 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");;								
							staff3.setPhysiciansNumber(physiciansNumber);
							staff3.setChiefPhysician(fchiefPhysician);
							staff3.setFchiefPhysician(fchiefPhysician);
							staff3.setAttendingNumber(attendingNumber);
							staff3.setResidentNumber(residentNumber);
							staff3.setDrNumber(drNumber);
							staff3.setMasterNumber(masterNumber);
							staff3.setBachelorNumber(bachelorNumber);
							general.put(ksDto.getHerniaSurgery(),staff3);
						}				
					}
					for(int i = 0;i<ksName.length;i++){		
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						   
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getBreastSurgery() != null && ksDto.getBreastSurgery().equals(name)){
							StaffDto staff4 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff4.setPhysiciansNumber(physiciansNumber);
							staff4.setChiefPhysician(fchiefPhysician);
							staff4.setFchiefPhysician(fchiefPhysician);
							staff4.setAttendingNumber(attendingNumber);
							staff4.setResidentNumber(residentNumber);
							staff4.setDrNumber(drNumber);
							staff4.setMasterNumber(masterNumber);
							staff4.setBachelorNumber(bachelorNumber);
							general.put(ksDto.getBreastSurgery(),staff4);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						   
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getVascularSurgery() != null && ksDto.getVascularSurgery().equals(name)){
							StaffDto staff5 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");							
							staff5.setPhysiciansNumber(physiciansNumber);
							staff5.setChiefPhysician(fchiefPhysician);
							staff5.setFchiefPhysician(fchiefPhysician);
							staff5.setAttendingNumber(attendingNumber);
							staff5.setResidentNumber(residentNumber);
							staff5.setDrNumber(drNumber);
							staff5.setMasterNumber(masterNumber);
							staff5.setBachelorNumber(bachelorNumber);
							general.put(ksDto.getVascularSurgery(),staff5);
						}				
					}
					if(!CommonUtils.isEmpty(general)){	
						String generalSurgery = general.toJSONString();
						ks.setGeneralSurgery(generalSurgery);
					}
					
					JSONObject thoracic = new JSONObject();
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getThoracicSurgery() != null && ksDto.getThoracicSurgery().equals(name)){
							StaffDto staff6 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff6.setPhysiciansNumber(physiciansNumber);
							staff6.setChiefPhysician(fchiefPhysician);
							staff6.setFchiefPhysician(fchiefPhysician);
							staff6.setAttendingNumber(attendingNumber);
							staff6.setResidentNumber(residentNumber);
							staff6.setDrNumber(drNumber);
							staff6.setMasterNumber(masterNumber);
							staff6.setBachelorNumber(bachelorNumber);
							thoracic.put(ksDto.getThoracicSurgery(),staff6);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getPtThoracicSurgery() != null && ksDto.getPtThoracicSurgery().equals(name)){
							StaffDto staff7 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff7.setPhysiciansNumber(physiciansNumber);
							staff7.setChiefPhysician(fchiefPhysician);
							staff7.setFchiefPhysician(fchiefPhysician);
							staff7.setAttendingNumber(attendingNumber);
							staff7.setResidentNumber(residentNumber);
							staff7.setDrNumber(drNumber);
							staff7.setMasterNumber(masterNumber);
							staff7.setBachelorNumber(bachelorNumber);
							thoracic.put(ksDto.getPtThoracicSurgery(),staff7);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getCardiacSurgery() != null && ksDto.getCardiacSurgery().equals(name)){
							StaffDto staff8 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff8.setPhysiciansNumber(physiciansNumber);
							staff8.setChiefPhysician(fchiefPhysician);
							staff8.setFchiefPhysician(fchiefPhysician);
							staff8.setAttendingNumber(attendingNumber);
							staff8.setResidentNumber(residentNumber);
							staff8.setDrNumber(drNumber);
							staff8.setMasterNumber(masterNumber);
							staff8.setBachelorNumber(bachelorNumber);
							thoracic.put(ksDto.getCardiacSurgery(),staff8);
						}				
					}
					if(!CommonUtils.isEmpty(thoracic)){					
						String thoracicSurgery = thoracic.toJSONString();
						ks.setThoracicSurgery(thoracicSurgery);
					}
					
					JSONObject urology = new JSONObject();
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getUrology() != null && ksDto.getUrology().equals(name)){
							StaffDto staff9 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
						    residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");							
							staff9.setPhysiciansNumber(physiciansNumber);
							staff9.setChiefPhysician(fchiefPhysician);
							staff9.setFchiefPhysician(fchiefPhysician);
							staff9.setAttendingNumber(attendingNumber);
							staff9.setResidentNumber(residentNumber);
							staff9.setDrNumber(drNumber);
							staff9.setMasterNumber(masterNumber);
							staff9.setBachelorNumber(bachelorNumber);
							urology.put(ksDto.getUrology(),staff9);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getUrologicalOncology() != null && ksDto.getUrologicalOncology().equals(name)){
							StaffDto staff10 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");						
							staff10.setPhysiciansNumber(physiciansNumber);
							staff10.setChiefPhysician(fchiefPhysician);
							staff10.setFchiefPhysician(fchiefPhysician);
							staff10.setAttendingNumber(attendingNumber);
							staff10.setResidentNumber(residentNumber);
							staff10.setDrNumber(drNumber);
							staff10.setMasterNumber(masterNumber);
							staff10.setBachelorNumber(bachelorNumber);
							urology.put(ksDto.getUrologicalOncology(),staff10);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getStonesBranch() != null && ksDto.getStonesBranch().equals(name)){
							StaffDto staff11 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");			
							staff11.setPhysiciansNumber(physiciansNumber);
							staff11.setChiefPhysician(fchiefPhysician);
							staff11.setFchiefPhysician(fchiefPhysician);
							staff11.setAttendingNumber(attendingNumber);
							staff11.setResidentNumber(residentNumber);
							staff11.setDrNumber(drNumber);
							staff11.setMasterNumber(masterNumber);
							staff11.setBachelorNumber(bachelorNumber);
							urology.put(ksDto.getStonesBranch(),staff11);
						}				
					}
					if(!CommonUtils.isEmpty(urology)){				
						String urologySurgery = urology.toJSONString();
						ks.setUrology(urologySurgery);
					}
					
					JSONObject women = new JSONObject();
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getWomenObstetrics() != null && ksDto.getWomenObstetrics().equals(name)){
							StaffDto staff12 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");					
							staff12.setPhysiciansNumber(physiciansNumber);
							staff12.setChiefPhysician(fchiefPhysician);
							staff12.setFchiefPhysician(fchiefPhysician);
							staff12.setAttendingNumber(attendingNumber);
							staff12.setResidentNumber(residentNumber);
							staff12.setDrNumber(drNumber);
							staff12.setMasterNumber(masterNumber);
							staff12.setBachelorNumber(bachelorNumber);
							women.put(ksDto.getWomenObstetrics(),staff12);
						}				
					}
					for(int i = 0;i<ksName.length;i++){		
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getGynecologicOncology() != null && ksDto.getGynecologicOncology().equals(name)){
							StaffDto staff13 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff13.setPhysiciansNumber(physiciansNumber);
							staff13.setChiefPhysician(fchiefPhysician);
							staff13.setFchiefPhysician(fchiefPhysician);
							staff13.setAttendingNumber(attendingNumber);
							staff13.setResidentNumber(residentNumber);
							staff13.setDrNumber(drNumber);
							staff13.setMasterNumber(masterNumber);
							staff13.setBachelorNumber(bachelorNumber);
							women.put(ksDto.getGynecologicOncology(),staff13);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getPtGynaecology() != null && ksDto.getPtGynaecology().equals(name)){
							StaffDto staff14 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff14.setPhysiciansNumber(physiciansNumber);
							staff14.setChiefPhysician(fchiefPhysician);
							staff14.setFchiefPhysician(fchiefPhysician);
							staff14.setAttendingNumber(attendingNumber);
							staff14.setResidentNumber(residentNumber);
							staff14.setDrNumber(drNumber);
							staff14.setMasterNumber(masterNumber);
							staff14.setBachelorNumber(bachelorNumber);
							women.put(ksDto.getPtGynaecology(),staff14);
						}				
					}
					if(!CommonUtils.isEmpty(women)){				
						String womenObstetrics = women.toJSONString();
						ks.setWomenObstetrics(womenObstetrics);
					}
					
					JSONObject orthopaedic = new JSONObject();
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getOrthopaedic() != null && ksDto.getOrthopaedic().equals(name)){
							StaffDto staff15 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff15.setPhysiciansNumber(physiciansNumber);
							staff15.setChiefPhysician(fchiefPhysician);
							staff15.setFchiefPhysician(fchiefPhysician);
							staff15.setAttendingNumber(attendingNumber);
							staff15.setResidentNumber(residentNumber);
							staff15.setDrNumber(drNumber);
							staff15.setMasterNumber(masterNumber);
							staff15.setBachelorNumber(bachelorNumber);								
							orthopaedic.put(ksDto.getOrthopaedic(),staff15);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getSpineSurgery() != null && ksDto.getSpineSurgery().equals(name)){
							StaffDto staff16 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");							
							staff16.setPhysiciansNumber(physiciansNumber);
							staff16.setChiefPhysician(fchiefPhysician);
							staff16.setFchiefPhysician(fchiefPhysician);
							staff16.setAttendingNumber(attendingNumber);
							staff16.setResidentNumber(residentNumber);
							staff16.setDrNumber(drNumber);
							staff16.setMasterNumber(masterNumber);
							staff16.setBachelorNumber(bachelorNumber);	
							orthopaedic.put(ksDto.getSpineSurgery(),staff16);
						}				
					}
					for(int i = 0;i<ksName.length;i++){	
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getJointSurgery() != null && ksDto.getJointSurgery().equals(name)){
							StaffDto staff17 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");								
							staff17.setPhysiciansNumber(physiciansNumber);
							staff17.setChiefPhysician(fchiefPhysician);
							staff17.setFchiefPhysician(fchiefPhysician);
							staff17.setAttendingNumber(attendingNumber);
							staff17.setResidentNumber(residentNumber);
							staff17.setDrNumber(drNumber);
							staff17.setMasterNumber(masterNumber);
							staff17.setBachelorNumber(bachelorNumber);
							orthopaedic.put(ksDto.getJointSurgery(),staff17);
						}				
					}
					if(!CommonUtils.isEmpty(orthopaedic)){				
						String orthopae = orthopaedic.toJSONString();
						ks.setOrthopaedic(orthopae);
					}
					
					JSONObject radioactive = new JSONObject();
					for(int i = 0;i<ksName.length;i++){		
						String name = ksName[i].replace("[", "").replace("]", "").replace("\"", "");
						if (name.indexOf("\\u") == -1){
							//如果不是unicode码则原样返回 
						}else{
							StringBuffer string = new StringBuffer();  
						    String[] hex = name.split("\\\\u");  
						    for (Integer j = 1; j < hex.length; j++) {  
						        // 转换出每一个代码点  
						        int data = Integer.parseInt(hex[j], 16);  
						        // 追加成string  
						        string.append((char) data);  
						        name=string.toString();
						    }  
						}
						if(ksDto.getRadioactive() != null && ksDto.getRadioactive().equals(name)){
							StaffDto staff18 = new StaffDto();
							physiciansNumber = ksStaffDto.getPhysiciansNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							chiefPhysician = ksStaffDto.getChiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							fchiefPhysician = ksStaffDto.getFchiefPhysician()[i].replace("[", "").replace("]", "").replace("\"", "");
							attendingNumber = ksStaffDto.getAttendingNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							residentNumber = ksStaffDto.getResidentNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							drNumber = ksStaffDto.getDrNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							masterNumber = ksStaffDto.getMasterNumber()[i].replace("[", "").replace("]", "").replace("\"", "");
							bachelorNumber = ksStaffDto.getBachelorNumber()[i].replace("[", "").replace("]", "").replace("\"", "");						
							staff18.setPhysiciansNumber(physiciansNumber);
							staff18.setChiefPhysician(fchiefPhysician);
							staff18.setFchiefPhysician(fchiefPhysician);
							staff18.setAttendingNumber(attendingNumber);
							staff18.setResidentNumber(residentNumber);
							staff18.setDrNumber(drNumber);
							staff18.setMasterNumber(masterNumber);
							staff18.setBachelorNumber(bachelorNumber);
							radioactive.put(ksDto.getRadioactive(),staff18);
						}				
					}
					if(!CommonUtils.isEmpty(radioactive)){				
						String radio = radioactive.toJSONString();
						ks.setRadioactive(radio);
					}
					ks.setHospitalId(hospital.getId());
					ksService.addKs(ks);			
								
				}
				if(!CommonUtils.isEmpty(equipmentDto)){	
					Equipment equipment = new Equipment();
					json.put("qNumber", equipmentDto.getqNumber());
					json.put("qImportNumber", equipmentDto.getqImportNumber());
					json.put("qImportBrand", equipmentDto.getqImportBrand());
					json.put("qDomesticNumber", equipmentDto.getqDomesticNumber());
					json.put("qDomesticBrand", equipmentDto.getqDomesticBrand());
					String qiangjinji = json.toJSONString();
					equipment.setQiangjinji(qiangjinji);
					
					JSONObject json1 = new JSONObject();
					json1.put("gfqNumber", equipmentDto.getGfqNumber());
					json1.put("gfqImportNumber", equipmentDto.getGfqImportNumber());
					json1.put("standardfq", equipmentDto.getStandardfq());
					json1.put("domesticsfq", equipmentDto.getDomesticsfq());
					String fqjinji = json1.toJSONString();
					equipment.setFqjinji(fqjinji);
					
					JSONObject json2 = new JSONObject();
					json2.put("treeYeanfqNumber", equipmentDto.getTreeYeanfqNumber());
					json2.put("daySurNumber", equipmentDto.getDaySurNumber());
					json2.put("commonlyNumber", equipmentDto.getCommonlyNumber());
					json2.put("standbyNumber", equipmentDto.getStandbyNumber());
					json2.put("freeNumber", equipmentDto.getFreeNumber());
					String newplaceFqjinji = json2.toJSONString();
					equipment.setNewplaceFqjinji(newplaceFqjinji);
					
					JSONObject json3 = new JSONObject();
					json3.put("treeYeaSurgeryYw", equipmentDto.getTreeYeaSurgeryYw());
					json3.put("example", equipmentDto.getExample());
					String carryQsurgery = json3.toJSONString();
					equipment.setCarryQsurgery(carryQsurgery);
					String supplyState = equipmentDto.getSupplyState();
					equipment.setSupplyState(supplyState);
					
					JSONObject json4 = new JSONObject();
					json4.put("disinWay", equipmentDto.getDisinWay());
					json4.put("liquidType", equipmentDto.getLiquidType());
					json4.put("manufacturer", equipmentDto.getManufacturer());
					json4.put("disinTime",equipmentDto.getDisinTime());
					String qiangjiqiDisin = json4.toJSONString();
					equipment.setQiangjiqiDisin(qiangjiqiDisin);	
						equipment.setHospitalId(hospital.getId());			
						equipmentService.addEquipment(equipment);			
				} 
				if(!CommonUtils.isEmpty(clinicalApplicationDto)){			
					ClinicalApplication clinicalApplication = new ClinicalApplication();
					
					JSONObject json11 = new JSONObject();
					json11.put("fqLocalSurgery", clinicalApplicationDto.getFqLocalSurgery());
					json11.put("fqLeftSurgery", clinicalApplicationDto.getFqLeftSurgery());
					json11.put("fqLeftSideSurgery", clinicalApplicationDto.getFqLeftSideSurgery());
					json11.put("fqRightSideSurgery", clinicalApplicationDto.getFqRightSideSurgery());
					String operation = json11.toJSONString();
					clinicalApplication.setOperation(operation);
					
					JSONObject json5 = new JSONObject();
					json5.put("gndgProportion", clinicalApplicationDto.getGndgProportion());
					json5.put("gzlProportion", clinicalApplicationDto.getGzlProportion());
					String fqsimilarProportion = json5.toJSONString();
					clinicalApplication.setFqsimilarProportion(fqsimilarProportion);
					
					JSONObject json6 = new JSONObject();
					json6.put("kfProportion", clinicalApplicationDto.getKfProportion());
					json6.put("proportion", clinicalApplicationDto.getProportion());
					String speedKfsurgical = json6.toJSONString();
					clinicalApplication.setSpeedKfsurgical(speedKfsurgical);
					
					
					JSONObject json10 = new JSONObject();
					json10.put("medical", clinicalApplicationDto.getMedical());
					json10.put("cmDisease", clinicalApplicationDto.getCmDisease());
					json10.put("lackDoctor", clinicalApplicationDto.getLackDoctor());
					json10.put("lackEquipment", clinicalApplicationDto.getLackEquipment());
					json10.put("otherReasons", clinicalApplicationDto.getOtherReasons());
					json10.put("reasons", clinicalApplicationDto.getReasons());
					String minimallyWhy = json10.toJSONString();
					clinicalApplication.setMinimallyWhy(minimallyWhy);
					
					JSONObject json7 = new JSONObject();
					json7.put("zkfProportion", clinicalApplicationDto.getZkfProportion());
					json7.put("fqldNumber", clinicalApplicationDto.getFqldNumber());
					json7.put("ddssNumber", clinicalApplicationDto.getDdssNumber());
					json7.put("bleedingNumber", clinicalApplicationDto.getBleedingNumber());
					json7.put("dzgjsNumber", clinicalApplicationDto.getDzgjsNumber());
					json7.put("otherNumber", clinicalApplicationDto.getOtherNumber());
					String zsurgeryProportion = json7.toJSONString();
					clinicalApplication.setZsurgeryProportion(zsurgeryProportion);
					
					JSONObject json8 = new JSONObject();
					json8.put("operationTime", clinicalApplicationDto.getOperationTime());
					json8.put("bleedingMl", clinicalApplicationDto.getBleedingMl());
					json8.put("portazsNumber", clinicalApplicationDto.getPortazsNumber());
					json8.put("portazsTime", clinicalApplicationDto.getPortazsTime());
					String zsurgery = json8.toJSONString();
					clinicalApplication.setZsurgery(zsurgery);
					
					JSONObject json9 = new JSONObject();
					json9.put("twoSurgeryNumber", clinicalApplicationDto.getTwoSurgeryNumber());
					json9.put("bfzBleedingNumber", clinicalApplicationDto.getBfzBleedingNumber());
					json9.put("bfzFistulaNumber", clinicalApplicationDto.getBfzFistulaNumber());
					json9.put("bfzQkgrNumber", clinicalApplicationDto.getBfzQkgrNumber());
					json9.put("bfzFqgrNumber", clinicalApplicationDto.getBfzFqgrNumber());
					json9.put("bfzOtherNumber", clinicalApplicationDto.getBfzOtherNumber());
					String complications = json9.toJSONString();
					clinicalApplication.setComplications(complications);
					clinicalApplication.setHospitalId(hospital.getId());
					clinicalApplicationService.addClinicalApplication(clinicalApplication);							
				}
				
			}else{
				flag = 2;
				json.put("flag", flag);
		    }
	     }else{
		        flag = 1;
		        json.put("flag", flag);
	     }		
     }
}
