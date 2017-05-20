package controllers;

import models.Member;

/**
 * Created by Patrick on 13/05/2017.
 */
public interface IGymApi {
    void add(Member member);

    void removeMember(int index);

}
