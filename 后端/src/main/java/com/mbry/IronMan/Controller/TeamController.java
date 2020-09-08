package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.TeamRequestBody.CreateTeamRequest;
import com.mbry.IronMan.RequestBody.TeamRequestBody.JoinTeamRequest;
import com.mbry.IronMan.RequestBody.TeamRequestBody.LeaveTeamRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.TeamResponseBody.GetTeamResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController("/api/union")
public class TeamController{
    @GetMapping(value="/getTeam/")
    public GetTeamResponse getTeam(@RequestParam String cardId) {
        return null;
    }
    
    @PostMapping(value="/createTeam/")
    public DefaultResponse createTeam(@RequestBody CreateTeamRequest createTeamRequest) {
        return null;
    }
    
    @PostMapping(value="/joinTeam/")
    public DefaultResponse joinTeam(@RequestBody JoinTeamRequest joinTeamRequest) {
        return null;
    }

    @PostMapping(value="/leaveTeam/")
    public DefaultResponse leaveTeam(@RequestBody LeaveTeamRequest entity) {
        return null;
    }
}