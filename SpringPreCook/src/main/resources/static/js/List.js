/**
 * ユーザー一覧画面
 */
$(function() {
	// テーブルの行をクリックしたときの処理
	$('#colunmnList tbody tr').on('click', function() {
		// すべての行の選択状態を解除
		$('#colunmnList tbody tr').removeClass('table-row-active');
		// クリックされた行に選択状態のクラスを追加
		$(this).addClass('table-row-active');
		// 更新ボタン、削除ボタンを活性化
		$('#editBtn').removeAttr('disabled');
		$('#deleteDummyBtn').removeAttr('disabled');

		// ログインID一時保管
		editSelectedId($(this));

		editSelectedDetailId($(this));
	});

	$('#deleteOkBtn').click(function() {
		$('#deleteBtn').trigger('click');
	});
});

/**
 * テーブルで選択された行のログインIDを画面のhidden要素に保管します。
 * 
 * @param row 選択された行情報
 */
function editSelectedId(row) {
	row.find('td').each(function() {
		var columnId = $(this).attr('id');
		if (columnId.startsWith('id_')) {
			$('#selectedId').val($(this).text());
			return false;
		}
	});
}

function editSelectedDetailId(row) {
	row.find('td').each(function() {
		var columnId = $(this).attr('id');
		if (columnId.startsWith('id_')) {
			$('#selectedDetailId').val($(this).text());
			return false;
		}
	});
}


