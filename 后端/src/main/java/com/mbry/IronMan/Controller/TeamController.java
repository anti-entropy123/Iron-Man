package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.TeamRequestBody.CreateTeamRequest;
import com.mbry.IronMan.RequestBody.TeamRequestBody.JoinTeamRequest;
import com.mbry.IronMan.RequestBody.TeamRequestBody.LeaveTeamRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.TeamResponseBody.GetTeamResponse;
import com.mbry.IronMan.Service.TeamService;
import com.mbry.IronMan.Utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/union")
public class TeamController{
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("jwt.tokenHead")
    private String tokenHead;

    @GetMapping(value="/getTeam/")
    @PreAuthorize("hasRole('common')")
    public GetTeamResponse getTeam(
            @RequestParam String cardId,
            @RequestHeader HttpHeaders headers) {

        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String userId = jwtTokenUtil.getOpenIdFromToken(token);
        return teamService.getTeam(cardId, userId);
    }
    
    @PostMapping(value="/createTeam/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse createTeam(@RequestBody CreateTeamRequest createTeamRequest) {
        return teamService.createTeam(
            createTeamRequest.getUserId(), 
            createTeamRequest.getCardId(), 
            createTeamRequest.getMaxNum());
    }
    
    @PostMapping(value="/joinTeam/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse joinTeam(@RequestBody JoinTeamRequest joinTeamRequest) {
        return teamService.joinTeam(
            joinTeamRequest.getTeamId(), 
            joinTeamRequest.getUserId());
    }

    @PostMapping(value="/leaveTeam/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse leaveTeam(@RequestBody LeaveTeamRequest leaveTeamRequest) {
        return teamService.leaveTeam(leaveTeamRequest.getTeamId(), leaveTeamRequest.getUserId());
    }
}