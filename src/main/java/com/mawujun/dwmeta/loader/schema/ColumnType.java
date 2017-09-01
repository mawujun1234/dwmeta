package com.mawujun.dwmeta.loader.schema;
/**
 * 不同数据库的列类型。
 * DatabaseMetaData.getTypeInfo()
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public class ColumnType {
	private String type_name;//Type name
	private Integer data_type;//sql data type from java.sql.types
	//如果是null就表示这个类型不能设置长度
	private Integer precision;//长度，如果是数字类型就是数字的总长度，如果是字符类型就是字符的长度。如果是时间类型就是。如果是二进制类型就是二进制的长度。如果是null就表示这个类型不适合设置长度
	private String literal_prefix;//prefix used to quote a literal (may be <code>null</code>)
	private String literal_suffix;//suffix used to quote a literal (may be <code>null</code>)
	private String create_params;//parameters used in creating   the type (may be <code>null</code>)
	private Short nullable;//0:不允许为空，1：允许为空。2：不知道是否可以为空
	private Boolean case_sensitive;//is it case sensitive.
	private Boolean searchable;
	private Boolean unsigned_attribute;
	private Boolean fixed_prec_scale;//can it be a money value.
	private Boolean auto_increment;//can it be used for an auto-increment value.
	private String local_type_name;//localized version of type name (may be <code>null</code>)
	
	private Short minimum_scale;
	private Short maximum_scale;
	private Integer num_prec_radix;//usually 2 or 10
	/**
	 * 返回true表示必须设置长度，否则就不能设置宽度
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	public Boolean getCanprecision(){
		if(precision==null || precision<=0){
			return false;
		}
		return true;
	}
	
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getData_type() {
		return data_type;
	}
	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}
	public Integer getPrecision() {
		return precision;
	}
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}
	public String getLiteral_prefix() {
		return literal_prefix;
	}
	public void setLiteral_prefix(String literal_prefix) {
		this.literal_prefix = literal_prefix;
	}
	public String getLiteral_suffix() {
		return literal_suffix;
	}
	public void setLiteral_suffix(String literal_suffix) {
		this.literal_suffix = literal_suffix;
	}
	public String getCreate_params() {
		return create_params;
	}
	public void setCreate_params(String create_params) {
		this.create_params = create_params;
	}
	public Short getNullable() {
		return nullable;
	}
	public void setNullable(Short nullable) {
		this.nullable = nullable;
	}
	public Boolean isCase_sensitive() {
		return case_sensitive;
	}
	public void setCase_sensitive(Boolean case_sensitive) {
		this.case_sensitive = case_sensitive;
	}
	public Boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}
	public Boolean isUnsigned_attribute() {
		return unsigned_attribute;
	}
	public void setUnsigned_attribute(Boolean unsigned_attribute) {
		this.unsigned_attribute = unsigned_attribute;
	}
	public Boolean isFixed_prec_scale() {
		return fixed_prec_scale;
	}
	public void setFixed_prec_scale(Boolean fixed_prec_scale) {
		this.fixed_prec_scale = fixed_prec_scale;
	}
	public Boolean isAuto_increment() {
		return auto_increment;
	}
	public void setAuto_increment(Boolean auto_increment) {
		this.auto_increment = auto_increment;
	}
	public String getLocal_type_name() {
		return local_type_name;
	}
	public void setLocal_type_name(String local_type_name) {
		this.local_type_name = local_type_name;
	}
	public Short getMinimum_scale() {
		return minimum_scale;
	}
	public void setMinimum_scale(Short minimum_scale) {
		this.minimum_scale = minimum_scale;
	}
	public Short getMaximum_scale() {
		return maximum_scale;
	}
	public void setMaximum_scale(Short maximum_scale) {
		this.maximum_scale = maximum_scale;
	}
	public Integer getNum_prec_radix() {
		return num_prec_radix;
	}
	public void setNum_prec_radix(Integer num_prec_radix) {
		this.num_prec_radix = num_prec_radix;
	}
	
}
