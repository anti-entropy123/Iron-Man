package com.mbry.IronMan.DaoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Application.Application;
import com.mbry.IronMan.BusinessObject.Application.CardApplication;
import com.mbry.IronMan.BusinessObject.Application.TeamApplication;
import com.mbry.IronMan.Dao.ApplicationDao;
import com.mbry.IronMan.Mapper.ApplicationMapper;
import com.mbry.IronMan.Mapper.TeamApplicationMapper;
import com.mbry.IronMan.entity.ApplicationEntity;
import com.mbry.IronMan.entity.TeamApplicationEntity;

@Service
public class ApplicationDaoImp implements ApplicationDao {
	
	@Autowired
	private ApplicationMapper applicationMapper;
	@Autowired
	private TeamApplicationMapper teamApplicationMapper;

	@Override
	public String createApplication(CardApplication app) {
		ApplicationEntity applicationEntity = this.getApplicationEntityFromBO(app);
		applicationMapper.insertApplication(applicationEntity);
		return applicationEntity.getApplicationId();
	}

	@Override
	public String createApplication(TeamApplication app) {
		TeamApplicationEntity teamApplicationEntity = this.getTeamApplicationEntityFromBO(app);
		teamApplicationMapper.insertTeamApplication(teamApplicationEntity);
		return teamApplicationEntity.getApplicationId();
	}

	@Override
	public boolean processApplication(TeamApplication app) {
		try {
			teamApplicationMapper.updateStatusByTeamApplicationId(app.getApplicationId(), true);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean processApplication(CardApplication app) {
		try {
			applicationMapper.updateStatusByApplicationId(app.getApplicationId(), true);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Application[] queryApplicationByUserId(String targetUserId) {
		ApplicationEntity[] applicationEntitys = applicationMapper.queryApplicationsByTargetUserId(targetUserId);
		TeamApplicationEntity[] teamApplicationEntitys = teamApplicationMapper.queryTeamApplicationsByTargetUserId(targetUserId);
		List<Application> applications = new ArrayList<Application>();
		for (int i = 0; i < applicationEntitys.length; i++) {
			applications.add(getApplicationFromEntity(applicationEntitys[i]));
		}
		for (int i = 0; i < teamApplicationEntitys.length; i++) {
			applications.add(getApplicationFromEntity(teamApplicationEntitys[i]));
		}
		return applications.toArray(new Application[applications.size()]);
	}

	@Override
	public Application[] queryCardApplicationsByCardId(String cardId) {
		ApplicationEntity[] applicationEntitys = applicationMapper.queryApplicationByCardId(cardId);
		List<Application> applications = new ArrayList<Application>();
		for (ApplicationEntity ae: applicationEntitys) {
			applications.add(this.getApplicationFromEntity(ae));
		}
		return applications.toArray(new Application[applications.size()]);
	}

	@Override
	public Application queryApplicationByAppId(String applicationId) {
		ApplicationEntity applicationEntity = applicationMapper.queryApplicationById(applicationId);
		if (applicationEntity.getCardId() == null) {
			return this.getApplicationFromEntity(teamApplicationMapper.queryApplicationById(applicationId));
		} else {
			return this.getApplicationFromEntity(applicationEntity);
		}
	}
	
	@Override
	public CardApplication[] queryCardAppByApplicantUserId(String applicantId) {
		ApplicationEntity[] applicationEntity = applicationMapper.queryApplicationsByApplicantId(applicantId);
		List<CardApplication> applications = new ArrayList<CardApplication>();
		for (ApplicationEntity ae: applicationEntity) {
			applications.add((CardApplication)(this.getApplicationFromEntity(ae)));
		}
		return applications.toArray(new CardApplication[applications.size()]);
	}
	/**
	 * 将CardApplication转为ApplicationEntity
	 * @param app
	 * @return
	 */
	private ApplicationEntity getApplicationEntityFromBO(CardApplication app) {
		ApplicationEntity applicationEntity = new ApplicationEntity();
		applicationEntity.setCardId(app.getCardId());
		applicationEntity.setApplicantId(app.getApplicantId());
		applicationEntity.setStatus(app.isStatus());
		applicationEntity.setTargetUserId(app.getTargetId());
		applicationEntity.setDate(app.getDate());
		return applicationEntity;
	}
	
	/**
	 * 将TeamApplication转为TeamApplicationEntity
	 * @param app
	 * @return
	 */
	private TeamApplicationEntity getTeamApplicationEntityFromBO(TeamApplication app) {
		TeamApplicationEntity teamApplicationEntity = new TeamApplicationEntity();
		teamApplicationEntity.setApplicationId(app.getApplicationId());
		teamApplicationEntity.setTeamId(app.getTeamId());
		teamApplicationEntity.setApplicantId(app.getApplicantId());
		teamApplicationEntity.setStatus(app.isStatus());
		teamApplicationEntity.setTargetUserId(app.getTargetId());
		teamApplicationEntity.setDate(app.getDate());
		return teamApplicationEntity;
	}
	
	/**
	 * 将ApplicationEntity转为Application
	 * @param app
	 * @return
	 */
	private Application getApplicationFromEntity(ApplicationEntity app) {
		CardApplication cardApplication = new CardApplication();
		cardApplication.setApplicationId(app.getApplicationId());
		cardApplication.setCardId(app.getCardId());
		cardApplication.setApplicantId(app.getApplicantId());
		cardApplication.setStatus(app.isStatus());
		cardApplication.setTargetId(app.getTargetUserId());
		cardApplication.setDate(app.getDate());
		return (Application)cardApplication;
	}
	
	/**
	 * 将TeamApplicationEntity转为Application
	 * @param app
	 * @return
	 */
	private Application getApplicationFromEntity(TeamApplicationEntity app) {
		TeamApplication teamApplication = new TeamApplication();
		teamApplication.setApplicationId(app.getApplicationId());
		teamApplication.setTeamId(app.getTeamId());
		teamApplication.setApplicantId(app.getApplicantId());
		teamApplication.setStatus(app.isStatus());
		teamApplication.setTargetId(app.getTargetUserId());
		teamApplication.setDate(app.getDate());
		return (Application)teamApplication;
	}

}
