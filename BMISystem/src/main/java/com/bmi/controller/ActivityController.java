package com.bmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmi.domain.ActivityGroupList;
import com.bmi.domain.ActivityList;
import com.bmi.domain.UserActivityList;
import com.bmi.service.ActivityService;
/**
 * Created by Cem �anal.
 */

@Controller
public class ActivityController {

	@Autowired
	public ActivityService actService;
	
	public void setAppService(ActivityService actService) {
		this.actService = actService;
	}
	
	@RequestMapping("/activityList")
	public String fetchShowActivities(
			Model model,
			ActivityList actList,
			ActivityGroupList actGrpList) {
		model.addAttribute("actList", actList);
		model.addAttribute("actGrpList", actGrpList);
		actService.JSONActivityFetching(actList, actGrpList);
		return "activityList";
	}
	
	@RequestMapping("/addActivity")
	public String addUsersActivity(
			Model model,
			@RequestParam(value = "duration") String duration,
			@RequestParam(value = "date") String date,
			@RequestParam(value = "actID") String actID,
			@RequestParam(value = "actDesc") String actDesc,
			ActivityList actList) {
		model.addAttribute("actID", actID);
		model.addAttribute("actDesc", actDesc);
		return actService.addUsersActivity(duration, date, actID, actDesc);
	}
	
    @RequestMapping(value = "/showActivity")
	public String showUsersActivity(Model model,
			ActivityList actList,
			UserActivityList uActList) {
    	model.addAttribute("actList", actList);
    	model.addAttribute("uActList", uActList);
    	return actService.showUsersActivity(actList, uActList);
	}
	
	@RequestMapping("/newActivitySuccess")
	public String actSuccessForm(Model model,
			@ModelAttribute("actID") String actID,
			@ModelAttribute("actDesc") String actDesc) {
		return "newActivitySuccess";
	}

	@RequestMapping("/newActivityFailed")
	public String actFailForm(Model model) {
		return "newActivityFailed";
	}
}
