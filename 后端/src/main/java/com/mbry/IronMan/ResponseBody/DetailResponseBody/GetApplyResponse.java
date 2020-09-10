package com.mbry.IronMan.ResponseBody.DetailResponseBody;

import java.io.Serializable;
import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetApplyResponse extends DefaultResponse {
    /**
     *
     */
    private static final long serialVersionUID = 7922674754913378930L;
    
    public class Data implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -4063232411971374186L;

        public class Person implements Serializable {
            /**
             *
             */
            private static final long serialVersionUID = -7765363165514705844L;
            private String userId;
            private String avatarUrl;
            private String name;
            private String applyId;

            public Person() {
            }
        
            public Person(
                    String userId,
                    String avatarUrl,
                    String name,
                    String applyId) {
                this.userId = userId;
                this.avatarUrl = avatarUrl;
                this.name = name;
                this.applyId = applyId;
            }

            public Person(com.mbry.IronMan.BusinessObject.User user, String applyId){
                this.userId = user.getUserId();
                this.avatarUrl = user.getAvatar();
                this.name = user.getNickname();
                this.applyId = applyId;
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

            public String getName() {
                return this.name;
            }
        
            public void setName(String name) {
                this.name = name;
            }

            public String getApplyId() {
                return this.applyId;
            }
        
            public void setApplyId(String applyId) {
                this.applyId = applyId;
            }
        }
        public class Team implements Serializable {
            /**
             *
             */
            private static final long serialVersionUID = -8756289951362554137L;
            private String teamId;
            
            public class Member{
                private String userId;
                private String avatarUrl;
                
                public Member(){}
                public Member(
                        String userId,
                        String avatarUrl
                        ){
                    this.userId = userId;
                    this.avatarUrl = avatarUrl;
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
            private String applyId;

            public Team() {
            }
        
            public Team(
                    String teamId,
                    Member[] members,
                    String applyId) {
                this.teamId = teamId;
                this.members = members;
                this.applyId = applyId;
                
            }
        
            public String getTeamId() {
                return this.teamId;
            }
        
            public void setTeamId(String teamId) {
                this.teamId = teamId;
            }

            public Member[] getMembers() {
                return this.members;
            }
        
            public void setMembers(Member[] members) {
                this.members = members;
            }

            public String getApplyId() {
                return this.applyId;
            }
        
            public void setApplyId(String applyId) {
                this.applyId = applyId;
            }
        }

        private Person[] person;
        private Team[] teams;

        public Data() {
        }
    
        public Data(
                Person[] person,
                Team[] teams) {
            this.person = person;
            this.teams = teams;
        }
    
        public Person[] getPerson() {
            return this.person;
        }
    
        public void setPerson(Person[] person) {
            this.person = person;
        }

        public Team[] getTeams() {
            return this.teams;
        }
    
        public void setTeams(Team[] teams) {
            this.teams = teams;
        }
    }
    
    private Data data;
    public GetApplyResponse(){}
    public GetApplyResponse(
            Data data,
            int result,
            String message
    ){
        super(result, message);
        this.data = data;
    }

    public Data getData(){
        return this.data;
    }
    public void setData(Data data){
        this.data = data;
    }
}