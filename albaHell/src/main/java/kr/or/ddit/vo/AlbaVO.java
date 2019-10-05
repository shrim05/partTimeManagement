package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class AlbaVO implements Serializable{
	private String al_id;
	private String al_name;
	private Integer al_age;
	private String al_address;
	private String al_hp;
	private String al_spec;
	private String al_desc;
	private String gr_code;
	private String al_career;
	private String al_gen;
	private String al_btype;
	private String al_mail;
	private String gr_name;
	private List<LicenseVO> licList;
	
	@Override
	public String toString() {
		return "AlbaVO [al_id=" + al_id + ", al_name=" + al_name + ", al_age=" + al_age + ", al_address=" + al_address
				+ ", al_hp=" + al_hp + ", al_spec=" + al_spec + ", al_desc=" + al_desc + ", gr_code=" + gr_code
				+ ", al_career=" + al_career + ", al_gen=" + al_gen + ", al_btype=" + al_btype + ", al_mail=" + al_mail
				+ ", gr_name=" + gr_name + ", licList=" + licList + "]";
	}
	public String getAl_id() {
		return al_id;
	}
	public void setAl_id(String al_id) {
		this.al_id = al_id;
	}
	public String getAl_name() {
		return al_name;
	}
	public void setAl_name(String al_name) {
		this.al_name = al_name;
	}
	public Integer getAl_age() {
		return al_age;
	}
	public void setAl_age(Integer al_age) {
		this.al_age = al_age;
	}
	public String getAl_address() {
		return al_address;
	}
	public void setAl_address(String al_address) {
		this.al_address = al_address;
	}
	public String getAl_hp() {
		return al_hp;
	}
	public void setAl_hp(String al_hp) {
		this.al_hp = al_hp;
	}
	public String getAl_spec() {
		return al_spec;
	}
	public void setAl_spec(String al_spec) {
		this.al_spec = al_spec;
	}
	public String getAl_desc() {
		return al_desc;
	}
	public void setAl_desc(String al_desc) {
		this.al_desc = al_desc;
	}
	public String getGr_code() {
		return gr_code;
	}
	public void setGr_code(String gr_code) {
		this.gr_code = gr_code;
	}
	public String getAl_career() {
		return al_career;
	}
	public void setAl_career(String al_career) {
		this.al_career = al_career;
	}
	public String getAl_gen() {
		return al_gen;
	}
	public void setAl_gen(String al_gen) {
		this.al_gen = al_gen;
	}
	public String getAl_btype() {
		return al_btype;
	}
	public void setAl_btype(String al_btype) {
		this.al_btype = al_btype;
	}
	public String getAl_mail() {
		return al_mail;
	}
	public void setAl_mail(String al_mail) {
		this.al_mail = al_mail;
	}
	public String getGr_name() {
		return gr_name;
	}
	public void setGr_name(String gr_name) {
		this.gr_name = gr_name;
	}
	public List<LicenseVO> getLicList() {
		return licList;
	}
	public void setLicList(List<LicenseVO> licList) {
		this.licList = licList;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbaVO other = (AlbaVO) obj;
		if (al_id == null) {
			if (other.al_id != null)
				return false;
		} else if (!al_id.equals(other.al_id))
			return false;
		if (al_name == null) {
			if (other.al_name != null)
				return false;
		} else if (!al_name.equals(other.al_name))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((al_id == null) ? 0 : al_id.hashCode());
		result = prime * result + ((al_name == null) ? 0 : al_name.hashCode());
		return result;
	}
	
	
}
