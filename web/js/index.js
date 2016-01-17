var fid; // option target
var modify = function(target) {
	fid = target.attr("fid");
	$("#inputName").val(target.parent().parent().children('td[name=infoName]').html());
	$("#inputAge").val(target.parent().parent().children('td[name=infoAge]').html());
	$("#inputQq").val(target.parent().parent().children('td[name=infoQq]').html());
	$("#inputTel").val(target.parent().parent().children('td[name=infoTel]').html());
	$("#inputEmail").val(target.parent().parent().children('td[name=infoEmail]').html());
	$("#inputAddress").val(target.parent().parent().children('td[name=infoAddress]').html());
	$("#mask").fadeIn(200);
	$("#friendFrame").fadeIn(200);
}
$(function(){
	//init
	if ($("div#empty").css("display") == "block") {
		$("table#info").css("display", "none");
	} else {
		$("table#info").css("display", "block");
	}

	$("#stateBar").delay(3000).fadeOut(200);
	var resetContent = function(){
		$("#searchBar").val("");
		$("#thead").show();
		$("table#info tr").show();
	}
	$("li#addFriend").click(function(){
		fid = -1;
		resetContent();
		$("#inputName").val("Name");
		$("#inputAge").val("");
		$("#inputQq").val("");
		$("#inputTel").val("");
		$("#inputEmail").val("");
		$("#inputAddress").val("");
		$("#mask").fadeIn(200);
		$("#friendFrame").fadeIn(200);
	});
	$("a.modify").click(function(){
		modify($(this));
	});
	$("#searchBar").keyup(function() {
		var target = $("table#info tr").not("#thead").hide().filter(":contains('" + $(this).val() + "')");
		if (target.length == 0) {
			$("#thead").hide();
			$("#empty").show();
		} else {
			$("#thead").show();
			$("#empty").hide();
			target.show();
		}
	});
	$("#save").click(function(){
		var ok = true;
		$("#textarea input").each(function(){
			if ($(this).val() == "") {
				$(this).focus();
				ok = false;
			}
		});
		if (ok && isNaN($("#inputAge").val())) {
			$("#inputAge").val("must be a number.");
			$("#inputAge").focus();
			ok = false;
		}
		if (ok && $("#inputEmail").val().indexOf("@") < 0) {
			$("#inputEmail").val("wrong format.");
			$("#inputEmail").focus();
			ok = false;
		}
		var ageVal = parseInt($("#inputAge").val());
		if (ageVal < 1 || ageVal > 120) {
			$("#inputAge").val("invalid number. (1-120)");
			$("#inputAge").focus();
			ok = false;
		}
		if (!ok) return false;
		if (fid == -1) {
			// add friend
			$.ajax({
				url: "/friend/add",
				method: "post",
				data: {
					"id": fid,
					"name": $("#inputName").val(),
					"age": $("#inputAge").val(),
					"qq": $("#inputQq").val(),
					"tel": $("#inputTel").val(),
					"email": $("#inputEmail").val(),
					"address": $("#inputAddress").val(),
					"sex": $("#chooser").children("option:selected").html()
				},
				success: function(data){
					if (data == 0) {
						$("#stateBar").html("Friend has already exists.").fadeIn(200).delay(3000).fadeOut(100);
						return;
					}
					var $newNode = $("<tr></tr>");
					$newNode.attr("fid", data);
					$newNode.append('<td><input type="checkbox"></td>');
					$newNode.append('<td name="infoName">' + $("#inputName").val() + '</td>');
					$newNode.append('<td name="infoSex">' + $("#chooser").children("option:selected").html() + '</td>');
					$newNode.append('<td name="infoAge">' + $("#inputAge").val() + '</td>');
					$newNode.append('<td name="infoQq">' + $("#inputQq").val() + '</td>');
					$newNode.append('<td name="infoTel">' + $("#inputTel").val() + '</td>');
					$newNode.append('<td name="infoEmail">' + $("#inputEmail").val() + '</td>');
					$newNode.append('<td name="infoAddress">' + $("#inputAddress").val() + '</td>');
					$newNode.append('<td><a class="modify" fid="' + data + '" onclick="modify($(this));">Modify</a></td>');
					$("#empty").hide();
					$("table#info").show();
					$("table#info").append($newNode);
					$("html, body").animate({
						scrollTop: $(document).height()
					}, 200);
				}
			});
		} else {
			// modify friend
			$.ajax({
				url: "/friend/modify",
				method: "post",
				data: {
					"id": fid,
					"name": $("#inputName").val(),
					"age": $("#inputAge").val(),
					"qq": $("#inputQq").val(),
					"tel": $("#inputTel").val(),
					"email": $("#inputEmail").val(),
					"address": $("#inputAddress").val(),
					"sex": $("#chooser").children("option:selected").html()
				},
				success: function(data){
					if (data == "false") {
						$("#stateBar").html("Update failed.").fadeIn(200).delay(3000).fadeOut(100);
						return;
					}
					$("tr[fid=" + fid + "] td[name=infoName]").html($("#inputName").val());
					$("tr[fid=" + fid + "] td[name=infoSex]").html($("#chooser").children("option:selected").html());
					$("tr[fid=" + fid + "] td[name=infoAge]").html($("#inputAge").val());
					$("tr[fid=" + fid + "] td[name=infoQq]").html($("#inputQq").val());
					$("tr[fid=" + fid + "] td[name=infoTel]").html($("#inputTel").val());
					$("tr[fid=" + fid + "] td[name=infoEmail]").html($("#inputEmail").val());
					$("tr[fid=" + fid + "] td[name=infoAddress]").html($("#inputAddress").val());
					$("#stateBar").html("Update successful.").fadeIn(200).delay(3000).fadeOut(100);
				}
			});
		}
		resetContent();
		$("#mask").fadeOut(100);
		$("#friendFrame").fadeOut(100);
	});
	$("#mask").click(function(){
		$("#mask").fadeOut(100);
		$("#friendFrame").fadeOut(100);
	});
	$("#stateBar").click(function(){
		$(this).fadeOut(100);
	});
	$("#checkAll").click(function(){
		$(":checkbox").not("#checkAll").attr("checked", this.checked);
	});
	$(":checkbox").not("#checkAll").click(function(){
		$("#checkAll").attr("checked", false);
	});
	$("#delFriend").click(function(){
		var $checked = $(":checkbox:checked").not("#checkAll");
		if ($checked.size() == 0) {
			$("#stateBar").html("No item selected.").fadeIn(200).delay(3000).fadeOut(100);
		} else {
			$checked.each(function(){
				var targetId = $(this).parent("td").parent("tr").attr("fid");
				$.ajax({
					url: "/friend/delete",
					method: "post",
					data: {
						fid: targetId
					},
					success: function(data){
						if (data == "success") {
							$checked.parent("td").parent("tr").fadeOut(200);
							$checked.parent("td").parent("tr").remove();
							if ($("table#info tr").length == 1) {
								$("table#info").hide();
								$("#empty").show();
							} else {
								$("table#info").show();
								$("#empty").hide();
							}
						} else {
							$("#stateBar").html(data).fadeIn(200).delay(3000).fadeOut(100);
							return;
						}
					}
				});
			});
		}
	});
})