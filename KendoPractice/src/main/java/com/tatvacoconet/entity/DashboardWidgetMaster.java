package com.tatvacoconet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="widget")
public class DashboardWidgetMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "widget_id")
	private int widgetId;

	@Column(name = "widget_name", nullable = false, length = 255)
	private String widgetName;
	
	@Column(name = "widget_body", columnDefinition = "TEXT", nullable = false)
	private String widgetBody;

	@Column(name = "widget_isactive", nullable = false)
	private boolean widgetIsActive;
	
	@Column(name = "widget_displayorder", nullable = false)
	private int widgetDisplayOrder;
	
	@CreationTimestamp
	@Column(name = "widget_createdon")
	private Date createdOn;
	
	@UpdateTimestamp
	@Column(name = "widget_updatedon")
	private Date updatedOn;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "widgetformat_id")
	@JsonIgnore
	private WidgetFormatMaster widgetFormat;

	public DashboardWidgetMaster() {
	}

	public int getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(int widgetId) {
		this.widgetId = widgetId;
	}

	public String getWidgetName() {
		return widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	public String getWidgetBody() {
		return widgetBody;
	}

	public void setWidgetBody(String widgetBody) {
		this.widgetBody = widgetBody;
	}

	public boolean isWidgetIsActive() {
		return widgetIsActive;
	}

	public void setWidgetIsActive(boolean widgetIsActive) {
		this.widgetIsActive = widgetIsActive;
	}

	public int getWidgetDisplayOrder() {
		return widgetDisplayOrder;
	}

	public void setWidgetDisplayOrder(int widgetDisplayOrder) {
		this.widgetDisplayOrder = widgetDisplayOrder;
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

	public WidgetFormatMaster getWidgetFormat() {
		return widgetFormat;
	}

	public void setWidgetFormat(WidgetFormatMaster widgetFormat) {
		this.widgetFormat = widgetFormat;
	}
	
	

}
