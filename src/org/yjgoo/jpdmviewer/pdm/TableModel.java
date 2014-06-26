package org.yjgoo.jpdmviewer.pdm;

import java.util.ArrayList;
import java.util.List;

/**
 * Table Model Bean
 * 
 * @author yjgoo
 * 
 */
public class TableModel {
	private String id;
	private String objectId;
	private String name;
	private String code;
	private String creationDate;
	private String creator;
	private String modificationDate;
	private String modifier;
	private List<ColumnModel> cols = new ArrayList<ColumnModel>();
	private List<KeyModel> keys = new ArrayList<KeyModel>();
	// saved id reference
	private List<String> primaryKeys = new ArrayList<String>();

	public void addColumn(ColumnModel col){
		this.cols.add(col);
	}
	
	public void addKey(KeyModel key){
		this.keys.add(key);
	}
	
	public void addPrimaryKeyRef(String refKeyId){
		this.primaryKeys.add(refKeyId);
	}
	
	public int columnSize(){
		return this.cols.size();
	}
	
	public int keySize(){
		return this.keys.size();
	}
	
	public int primaryKeyRefSize(){
		return this.primaryKeys.size();
	}
	
	public List<ColumnModel> getCols(){
		return this.cols;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
