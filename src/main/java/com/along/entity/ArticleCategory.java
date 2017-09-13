package com.along.entity;

import java.util.Date;

public class ArticleCategory extends DataEntity<ArticleCategory>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.id
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.name
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.create_by
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.create_date
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.update_date
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.del_flag
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private String delFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.remarks
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private String remarks;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.update_by
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_article_category.blog_id
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    private String blogId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.id
     *
     * @return the value of t_article_category.id
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.id
     *
     * @param id the value for t_article_category.id
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.name
     *
     * @return the value of t_article_category.name
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.name
     *
     * @param name the value for t_article_category.name
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.create_by
     *
     * @return the value of t_article_category.create_by
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.create_by
     *
     * @param createBy the value for t_article_category.create_by
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.create_date
     *
     * @return the value of t_article_category.create_date
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.create_date
     *
     * @param createDate the value for t_article_category.create_date
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.update_date
     *
     * @return the value of t_article_category.update_date
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.update_date
     *
     * @param updateDate the value for t_article_category.update_date
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.del_flag
     *
     * @return the value of t_article_category.del_flag
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.del_flag
     *
     * @param delFlag the value for t_article_category.del_flag
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.remarks
     *
     * @return the value of t_article_category.remarks
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.remarks
     *
     * @param remarks the value for t_article_category.remarks
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.update_by
     *
     * @return the value of t_article_category.update_by
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.update_by
     *
     * @param updateBy the value for t_article_category.update_by
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_article_category.blog_id
     *
     * @return the value of t_article_category.blog_id
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public String getBlogId() {
        return blogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_article_category.blog_id
     *
     * @param blogId the value for t_article_category.blog_id
     *
     * @mbg.generated Tue Sep 05 21:20:03 CST 2017
     */
    public void setBlogId(String blogId) {
        this.blogId = blogId == null ? null : blogId.trim();
    }
}