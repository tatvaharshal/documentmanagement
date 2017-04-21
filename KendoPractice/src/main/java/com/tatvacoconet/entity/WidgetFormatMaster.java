package com.tatvacoconet.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author TatvaSoft
 *
 */
@Entity
@Table(name="widgetformat")
public class WidgetFormatMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "widgetformat_id")
	private int widgetFormatId;

	@Column(name = "widgetformat_name", nullable = false)
	private String widgetFormatName;
	
	@Column(name = "widgetformat_filepath", nullable = false, length = 500)
	private String widgetFormatFilepath;

	@Column(name = "widgetformat_description")
	private String widgetFormatDescription;
	
	@Column(name = "widgetformat_bgimageurl")
	private String widgetFormatBgImageUrl;
	
	@Column(name = "widgetformat_datasource")
	private String widgetFormatDatasource;
	
	@Column(name = "widgetformat_sql", length = 500)
	private String widgetFormatSQL;
	
	@Column(name = "widgetformat_width")
	private long widgetFormatWidth;
	
	@Column(name = "widgetformat_height")
	private long widgetFormatHeight;
	
	@CreationTimestamp
	@Column(name = "widgetformat_createdon")
	private Date createdOn;
	
	@UpdateTimestamp
	@Column(name = "widgetformat_updatedon")
	private Date updatedOn;
	
	@OneToMany(mappedBy = "widgetFormat", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<DashboardWidgetMaster> widgets;


	public int getWidgetFormatId() {
		return widgetFormatId;
	}
	public void setWidgetFormatId(int widgetFormatId) {
		this.widgetFormatId = widgetFormatId;
	}
	public String getWidgetFormatName() {
		return widgetFormatName;
	}
	public void setWidgetFormatName(String widgetFormatName) {
		this.widgetFormatName = widgetFormatName;
	}
	public String getWidgetFormatFilepath() {
		return widgetFormatFilepath;
	}
	public void setWidgetFormatFilepath(String widgetFormatFilepath) {
		this.widgetFormatFilepath = widgetFormatFilepath;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Set<DashboardWidgetMaster> getWidgets() {
		return widgets;
	}
	public void setWidgets(Set<DashboardWidgetMaster> widgets) {
		this.widgets = widgets;
	}
	public String getWidgetFormatDescription() {
		return widgetFormatDescription;
	}
	public void setWidgetFormatDescription(String widgetFormatDescription) {
		this.widgetFormatDescription = widgetFormatDescription;
	}
	public String getWidgetFormatBgImageUrl() {
		return widgetFormatBgImageUrl;
	}
	public void setWidgetFormatBgImageUrl(String widgetFormatBgImageUrl) {
		this.widgetFormatBgImageUrl = widgetFormatBgImageUrl;
	}
	public String getWidgetFormatDatasource() {
		return widgetFormatDatasource;
	}
	public void setWidgetFormatDatasource(String widgetFormatDatasource) {
		this.widgetFormatDatasource = widgetFormatDatasource;
	}
	public String getWidgetFormatSQL() {
		return widgetFormatSQL;
	}
	public void setWidgetFormatSQL(String widgetFormatSQL) {
		this.widgetFormatSQL = widgetFormatSQL;
	}
	public long getWidgetFormatWidth() {
		return widgetFormatWidth;
	}
	public void setWidgetFormatWidth(long widgetFormatWidth) {
		this.widgetFormatWidth = widgetFormatWidth;
	}
	public long getWidgetFormatHeight() {
		return widgetFormatHeight;
	}
	public void setWidgetFormatHeight(long widgetFormatHeight) {
		this.widgetFormatHeight = widgetFormatHeight;
	}

	




}
