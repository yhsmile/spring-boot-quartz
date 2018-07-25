package com.example.demo.enums;

public class UrgeEnum {

	
	public enum VoiceType {
		A("a","A公司","1"),
		B("b","B公司","2"),
		C("c","C公司","3"),
		D("d","D公司","4");
		
		public String value;
		public String desc;
		public String type;
		
		public static String getDesc(String type){
			for(VoiceType status : VoiceType.values()){
				if(status.type.equals(type)){
					return status.desc;
				}
			}
			return "";
		}
		
		private VoiceType(String value,String desc,String type){
			this.value = value;
			this.desc = desc;
			this.type = type;
		}
	}
	
	
	
	public enum voiceCollectionJobType{
		VOICE_DATA("dataJob","待催案件发送任务"),
		VOICE_RESULT("resultJob","案件催收结果处理任务"),
		VOICE_WAV("wavJob","案件催收语音文件处理任务");
		
		public String value;
		public String desc;
		
		private voiceCollectionJobType(String value,String desc){
			this.value = value;
			this.desc = desc;
		}
	
	}
}
