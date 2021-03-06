package com.along.entity;

import java.util.Date;

public class Blog extends DataEntity<Blog>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.id
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.name
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.status
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.category
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.create_by
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.create_date
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.update_date
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.del_flag
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String delFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.remarks
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String remarks;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.update_by
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.category_id
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    private String categoryId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.id
     *
     * @return the value of t_blog.id
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.id
     *
     * @param id the value for t_blog.id
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.name
     *
     * @return the value of t_blog.name
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.name
     *
     * @param name the value for t_blog.name
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.status
     *
     * @return the value of t_blog.status
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.status
     *
     * @param status the value for t_blog.status
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.category
     *
     * @return the value of t_blog.category
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.category
     *
     * @param category the value for t_blog.category
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.create_by
     *
     * @return the value of t_blog.create_by
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.create_by
     *
     * @param createBy the value for t_blog.create_by
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.create_date
     *
     * @return the value of t_blog.create_date
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.create_date
     *
     * @param createDate the value for t_blog.create_date
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.update_date
     *
     * @return the value of t_blog.update_date
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.update_date
     *
     * @param updateDate the value for t_blog.update_date
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.del_flag
     *
     * @return the value of t_blog.del_flag
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.del_flag
     *
     * @param delFlag the value for t_blog.del_flag
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.remarks
     *
     * @return the value of t_blog.remarks
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.remarks
     *
     * @param remarks the value for t_blog.remarks
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.update_by
     *
     * @return the value of t_blog.update_by
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.update_by
     *
     * @param updateBy the value for t_blog.update_by
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.category_id
     *
     * @return the value of t_blog.category_id
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.category_id
     *
     * @param categoryId the value for t_blog.category_id
     *
     * @mbg.generated Tue Sep 05 15:34:49 CST 2017
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }
}