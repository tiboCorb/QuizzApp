package com.example.quizzapp.quizzAppDb;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by tiboCorb on 28/03/2020
 */

@Entity
public class Result {

    // primary key
    @Id(autoincrement = true)
    private Long resultId;

    // Foreign key
    private Long userId;

    // Foreign key
    private Long quizzId;

    private Long score;

    @Generated(hash = 959418743)
    public Result(Long resultId, Long userId, Long quizzId, Long score) {
        this.resultId = resultId;
        this.userId = userId;
        this.quizzId = quizzId;
        this.score = score;
    }

    @Generated(hash = 1176609929)
    public Result() {
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizzId() {
        return quizzId;
    }

    public void setQuizzId(Long quizzId) {
        this.quizzId = quizzId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
