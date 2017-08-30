package com.mawujun.dwmeta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class DWLayerService extends AbstractService<DWLayer, String>{

	@Autowired
	private DWLayerRepository dWLayerRepository;
	@Autowired
	private ClassifyService classifyService;
	
	@Override
	public DWLayerRepository getRepository() {
		return dWLayerRepository;
	}
	@Override
	public String create(DWLayer entity) {
		String id= this.getRepository().create(entity);
		classifyService.createDefault(id);
		return id;
	}
	
	public void testConn(String jdbc_driver,String jdbc_url,String jdbc_username,String jdbc_password) {
		Connection conn = null; // 表示数据库的连接对象
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, jdbc_username, jdbc_password); // 2、连接数据库
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("驱动类没有加!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("测试连接失败!"+e.getMessage());
		} finally {
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 3、关闭数据库
		}
		
		
		
	}

}
