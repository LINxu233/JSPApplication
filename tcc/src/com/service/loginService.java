package com.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.dao.TAdminDAO;
import com.dao.TCaozuoyuanDAO;
import com.dao.TRuchangDAO;
import com.model.TAdmin;
import com.model.TCaozuoyuan;
import com.model.TRuchang;
import com.util.Util;

public class loginService
{
	private TAdminDAO adminDAO;
	private TCaozuoyuanDAO caozuoyuanDAO;
	private TRuchangDAO ruchangDAO;
	
	public TRuchangDAO getRuchangDAO()
	{
		return ruchangDAO;
	}
	public void setRuchangDAO(TRuchangDAO ruchangDAO)
	{
		this.ruchangDAO = ruchangDAO;
	}
	public TCaozuoyuanDAO getCaozuoyuanDAO()
	{
		return caozuoyuanDAO;
	}
	public void setCaozuoyuanDAO(TCaozuoyuanDAO caozuoyuanDAO)
	{
		this.caozuoyuanDAO = caozuoyuanDAO;
	}
	public TAdminDAO getAdminDAO()
	{
		return adminDAO;
	}
	public void setAdminDAO(TAdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}
	
	
	
	public String login(String userName,String userPw,int userType)
	{
		System.out.println("userType"+userType);
		try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result="no";
		
		if(userType==0)//系统管理员登陆
		{
			String sql="from TAdmin where userName=? and userPw=?";
			Object[] con={userName.trim(),userPw.trim()};
			List adminList=adminDAO.getHibernateTemplate().find(sql,con);
			if(adminList.size()==0)
			{
				 result="no";
			}
			else
			{
				 WebContext ctx = WebContextFactory.get(); 
				 HttpSession session=ctx.getSession(); 
				 TAdmin admin=(TAdmin)adminList.get(0);
				 session.setAttribute("userType", 0);
	             session.setAttribute("admin", admin);
	             result="yes";
			}
		}
		if(userType==1)
		{
			String sql="from TCaozuoyuan where loginname=? and loginpw=?";
			Object[] con={userName.trim(),userPw.trim()};
			List caozuoyuanList=caozuoyuanDAO.getHibernateTemplate().find(sql,con);
			if(caozuoyuanList.size()==0)
			{
				 result="no";
			}
			else
			{
				 WebContext ctx = WebContextFactory.get(); 
				 HttpSession session=ctx.getSession(); 
				 TCaozuoyuan caozuoyuan=(TCaozuoyuan)caozuoyuanList.get(0);
				 session.setAttribute("userType", 1);
	             session.setAttribute("caozuoyuan", caozuoyuan);
	             result="yes";
			}
		}
		if(userType==2)
		{
			
		}
		return result;
	}

    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		admin.setUserPw(userPwNew);
		
		adminDAO.getHibernateTemplate().update(admin);
		session.setAttribute("admin", admin);
		
		return "yes";
    }
    
    
    public String jisuanfeiyong(int id,String rushijian,String chushijian) throws ParseException
    {
    	String s="入场时间:"+rushijian+",出场时间:"+chushijian;
    	
    	int feiyong=5;
    	Date aa=Util.newDate1(rushijian);
    	Date bb=Util.newDate1(chushijian);
    	
    	long gg=bb.getTime()-aa.getTime();
    	long fenzhong=gg/1000/60;
    	
    	if(fenzhong<60)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong+"(人民币)";
    	}
    	if(fenzhong>60 && fenzhong<120)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*2+"(人民币)";
    	}
    	if(fenzhong>120 && fenzhong<180)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*3+"(人民币)";
    	}
    	if(fenzhong>180 && fenzhong<240)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*4+"(人民币)";
    	}
    	if(fenzhong>240 && fenzhong<300)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*5+"(人民币)";
    	}
    	if(fenzhong>300 && fenzhong<360)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*6+"(人民币)";
    	}
    	if(fenzhong>360 && fenzhong<420)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*7+"(人民币)";
    	}
    	if(fenzhong>420 && fenzhong<480)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*8+"(人民币)";
    	}
    	if(fenzhong>480 && fenzhong<540)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*9+"(人民币)";
    	}
    	if(fenzhong>540 && fenzhong<600)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+feiyong*10+"(人民币)";
    	}
    	if(fenzhong>600)
    	{
    		s=s+",停车时间是:"+fenzhong+"分钟，所需费用为："+100+"(人民币)";
    	}
    	
    	
    	TRuchang ruchang=ruchangDAO.findById(id);
    	ruchang.setChushijian(chushijian);
    	ruchang.setFeiyong(feiyong);
    	ruchangDAO.getHibernateTemplate().update(ruchang);
    	
    	return s;
    }
}
