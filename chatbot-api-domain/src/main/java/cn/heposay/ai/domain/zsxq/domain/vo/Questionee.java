package cn.heposay.ai.domain.zsxq.domain.vo;
/**
 * @author heposay
 * @description 知识星球提问者信息
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:08
 */
public class Questionee {

    private String user_id;

    private String name;

    private String avatar_url;

    private String description;

    private String location;

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getAvatar_url(){
        return this.avatar_url;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }

}