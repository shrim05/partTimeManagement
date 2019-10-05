package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Base64;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"lic_code","lic_name"})
public class LicenseVO implements Serializable{
	private String lic_code;
	private String lic_name;
	private byte[] lic_image;
	private String al_id;
	
	public String getAl_id() {
		return al_id;
	}


	public void setAl_id(String al_id) {
		this.al_id = al_id;
	}


	public String getLic_code() {
		return lic_code;
	}


	public void setLic_code(String lic_code) {
		this.lic_code = lic_code;
	}


	public String getLic_name() {
		return lic_name;
	}


	public void setLic_name(String lic_name) {
		this.lic_name = lic_name;
	}


	public byte[] getLic_image() {
		return lic_image;
	}


	public void setLic_image(byte[] lic_image) {
		this.lic_image = lic_image;
	}


	public String getLic_imageBase64() {
		if(lic_image==null) return null;
		return Base64.getEncoder().encodeToString(lic_image);
	}
}
