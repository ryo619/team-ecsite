package jp.co.internous.wings.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.wings.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {

	//購入履歴情報を登録する  @param destinationId 宛先情報ID  @param userId ユーザーID  @return 登録件数
	int insert(@Param("destinationId") int destinationId, @Param("userId") int userId);
	
	//userIdによって履歴を検索し、PurchaseHistoryDtoのリストを返却するメソッドを宣言。実行されるSQLは、XML内で定義している。
	List<PurchaseHistoryDto> findPurchaseHistory(@Param("userId") int userId);
	
	//ログインしているuserIdと一致するuser_idをstatus =０に更新して返却。
	@Update("UPDATE tbl_purchase_history SET status = 0, updated_at = now() WHERE user_id = #{userId}")
	int deleteHistory(int userId);
}
