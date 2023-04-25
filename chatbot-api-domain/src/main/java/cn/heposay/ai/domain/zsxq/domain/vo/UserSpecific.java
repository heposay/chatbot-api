package cn.heposay.ai.domain.zsxq.domain.vo;
/**
 * @author heposay
 * @description 知识星球用户特征
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:08
 */
public class UserSpecific {

    private boolean liked;

    private boolean subscribed;

    public void setLiked(boolean liked){
        this.liked = liked;
    }
    public boolean getLiked(){
        return this.liked;
    }
    public void setSubscribed(boolean subscribed){
        this.subscribed = subscribed;
    }
    public boolean getSubscribed(){
        return this.subscribed;
    }

}
