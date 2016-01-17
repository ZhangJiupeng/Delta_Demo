package com.action;

import com.delta.core.assembler.annotation.Detachable;
import com.delta.core.rover.RequestMethod;
import com.delta.core.rover.XForm;
import com.delta.core.rover.XFormConverter;
import com.delta.core.rover.XFormLoader;
import com.delta.core.rover.annotation.Controller;
import com.delta.core.rover.annotation.RequestMapping;
import com.delta.core.rover.except.XFormCastException;
import com.entity.Friend;
import com.entity.User;
import com.form.FriendForm;
import com.service.FriendService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(namespace = "/friend")
public class FriendAction {
    FriendService friendService;

    @Detachable
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @RequestMapping(patterns = "/list")
    public String listFriends(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            request.setAttribute("friends", friendService.list(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index.jsp";
    }

    @RequestMapping(patterns = "/add", method = RequestMethod.POST)
    public String addFriend(HttpServletRequest request, HttpServletResponse response) {
        Friend friend;
        User user = (User) request.getSession().getAttribute("user");
        try {
            XForm friendForm = XFormLoader.newInstance(request, FriendForm.class);
            friend = XFormConverter.cast(friendForm, Friend.class);
            friend.setT_user_id(user.getId());
        } catch (XFormCastException e) {
            return "out:" + e.getMessage();
        }
        try {
            return "out:" + friendService.add(friend);
        } catch (Exception e) {
            return "out:0";
        }
    }

    @RequestMapping(patterns = "/modify", method = RequestMethod.POST)
    public String updateFriend(HttpServletRequest request, HttpServletResponse response) {
        Friend friend;
        try {
            XForm friendForm = XFormLoader.newInstance(request, FriendForm.class);
            friend = XFormConverter.cast(friendForm, Friend.class);
            friend.setT_user_id(((User) request.getSession().getAttribute("user")).getId());
        } catch (XFormCastException e) {
            e.printStackTrace();
            return "out:false";
        }
        try {
            return "out:" + friendService.update(friend);
        } catch (Exception e) {
            e.printStackTrace();
            return "out:false";
        }
    }

    @RequestMapping(patterns = "/delete", method = RequestMethod.POST)
    public String deleteFriend(HttpServletRequest request, HttpServletResponse response) {
        int target;
        try {
            target = Integer.parseInt(request.getParameter("fid"));
        } catch (Exception e) {
            return "out:" + e.getMessage();
        }
        try {
            Friend friend = new Friend();
            friend.setT_user_id(((User) request.getSession().getAttribute("user")).getId());
            friend.setId(target);
            return "out:" + (friendService.delete(friend) ? "success" : "Nothing detached.");
        } catch (Exception e) {
            return "out:" + e.getMessage();
        }
    }

}
