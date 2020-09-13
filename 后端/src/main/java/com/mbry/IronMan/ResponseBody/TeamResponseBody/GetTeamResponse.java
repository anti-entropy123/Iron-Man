package com.mbry.IronMan.ResponseBody.TeamResponseBody;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetTeamResponse extends DefaultResponse {
    
    private static final long serialVersionUID = 1234570292639605091L;

    public class Data{
        public class Team{
            private String teamId;
            private int maxTeamMem;
            public class Member{
                private String userId;
                private String avatarUrl;

                public Member(){}
                public Member(
                        String avatarUrl,
                        String userId){
                    this.avatarUrl = avatarUrl;
                    this.userId = userId;
                }
                public Member(com.mbry.IronMan.BusinessObject.User user){
                    this.avatarUrl = user.getAvatar();
                    this.userId = user.getUserId();
                }

                public String getUserId() {
                    return this.userId;
                }
            
                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getAvatarUrl() {
                    return this.avatarUrl;
                }
            
                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }
            }

            private Member[] members;

            public Team(){}
            public Team(
                    String teamId,
                    int maxTeamMem,
                    Member[] members){
                this.teamId = teamId;
                this.maxTeamMem = maxTeamMem;
                this.members = members;
            }

            public Team(com.mbry.IronMan.BusinessObject.Team team){
                this.maxTeamMem = team.getMaxNum();
                com.mbry.IronMan.BusinessObject.User[] _members = team.getMembers();
                if(_members != null){
                    this.members = new Member[_members.length];
                    for(int i = 0;i < members.length;i++){
                        this.members[i] = new Member(_members[i]);
                    }
                }
                this.teamId = team.getTeamId();
            }

            public String getTeamId() {
                return this.teamId;
            }
        
            public void setTeamId(String teamId) {
                this.teamId = teamId;
            }

            public int getMaxTeamMem() {
                return this.maxTeamMem;
            }
        
            public void setMaxTeamMem(int maxTeamMem) {
                this.maxTeamMem = maxTeamMem;
            }

            public Member[] getMembers() {
                return this.members;
            }
        
            public void setMembers(Member[] members) {
                this.members = members;
            }
        }
        
        private Team myTeam;
        private Team[] otherTeams; 
    
        public Data() {
        }
    
        public Data(
                Team myTeam,
                Team[] otherTeams) {
            this.myTeam = myTeam;
            this.otherTeams = otherTeams;
        }

        public Data(
                com.mbry.IronMan.BusinessObject.Team myTeam,
                com.mbry.IronMan.BusinessObject.Team[] otherTeams){
            this.myTeam = myTeam==null? null: new Team(myTeam);
            this.otherTeams = new Team[otherTeams.length];
            for(int i=0;i < this.otherTeams.length; ++i){
                this.otherTeams[i] = new Team(otherTeams[i]);
            }
        }

        public Team getMyTeam() {
            return this.myTeam;
        }
    
        public void setMyTeam(Team myTeam) {
            this.myTeam = myTeam;
        }

        public Team[] getOtherTeams() {
            return this.otherTeams;
        }
    
        public void setOtherTeams(Team[] otherTeams) {
            this.otherTeams = otherTeams;
        }
    }

    private Data data;
    
    public GetTeamResponse() {
    }

    public GetTeamResponse(
            Data data,
            int result,
            String message) {
        super(result, message);
        this.data = data;
    }

    public GetTeamResponse(
            com.mbry.IronMan.BusinessObject.Team myTeam,
            com.mbry.IronMan.BusinessObject.Team[] otherTeams,
            int result,
            String message){
        super(result, message);
        this.data = new Data(myTeam, otherTeams);
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}