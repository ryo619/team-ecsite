package jp.co.internous.wings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.wings.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.wings.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.wings.model.session.LoginSession;

//PurchaseHistoryControllerクラスをコントローラーとして認識
@Controller

//()内のURLをクライアントからリクエストされた際にマッピングする
@RequestMapping("/wings/history")
public class PurchaseHistoryController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblPurchaseHistoryMapper tblPurchaseHistoryMapper;
	
	//ログイン状態で　DBから購入履歴情報を取得する。
	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.getUserId();
		List<PurchaseHistoryDto> purchaseHistory = tblPurchaseHistoryMapper.findPurchaseHistory(userId);
		m.addAttribute("purchaseHistory",purchaseHistory);
		m.addAttribute("loginSession",loginSession);
		
		return "purchase_history";
	}
	
	//削除ボタンが押されたときに購入履歴情報をすべての論理削除
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		int userId = loginSession.getUserId();
		int result = tblPurchaseHistoryMapper.deleteHistory(userId);
		
		return result > 0;
	}
}
