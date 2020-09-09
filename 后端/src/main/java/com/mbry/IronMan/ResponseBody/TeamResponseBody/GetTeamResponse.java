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

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}