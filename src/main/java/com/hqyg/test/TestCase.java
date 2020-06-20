package com.hqyg.test;


import java.io.IOException;
import java.util.List;



import com.github.crab2died.ExcelUtils;
import com.github.crab2died.annotation.ExcelField;
import com.github.crab2died.exceptions.Excel4JException;





public class TestCase {
    @ExcelField(title="ID")
	public String uuid;
    @ExcelField(title="是否运行")
    public String run;
    @ExcelField(title="系统")
    public String system;
    @ExcelField(title="系统名称")
    public String systemname;
    @ExcelField(title="用例名称")
    public String casename;
    @ExcelField(title="地址")
    public String url;
    @ExcelField(title="参数")
    public String params;
    @ExcelField(title="类型")
    public String type;
    @ExcelField(title="头部信息")
    public String header;
    @ExcelField(title="检查点")
    public String checkpoint;
    @ExcelField(title="关联")
    public String correlation;
    @ExcelField(title="前置")
    public String beforefunc;
    @ExcelField(title="数据库检查点")
    public String dbchecksql;
    @ExcelField(title="数据库异常")
    public String dbexpected;
	/*
	 * @ExcelField(title="") public static final long serialVersionUID = 1L;
	 */

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run == null ? null : run.trim();
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system == null ? null : system.trim();
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename == null ? null : casename.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public String getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint == null ? null : checkpoint.trim();
    }

    public String getCorrelation() {
        return correlation;
    }

    public void setCorrelation(String correlation) {
        this.correlation = correlation == null ? null : correlation.trim();
    }

    public String getBeforefunc() {
        return beforefunc;
    }

    public void setBeforefunc(String beforefunc) {
        this.beforefunc = beforefunc == null ? null : beforefunc.trim();
    }

    public String getDbchecksql() {
        return dbchecksql;
    }

    public void setDbchecksql(String dbchecksql) {
        this.dbchecksql = dbchecksql == null ? null : dbchecksql.trim();
    }

    public String getDbexpected() {
        return dbexpected;
    }

    public void setDbexpected(String dbexpected) {
        this.dbexpected = dbexpected == null ? null : dbexpected.trim();
    }

  

    
    public static void main(String[] args) throws Exception, Excel4JException, IOException   {
    	String path="C:\\Users\\Administrator\\Desktop\\test.xls";
    	List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
   	 
   	   System.out.println(list);
	
    
    }
  
}