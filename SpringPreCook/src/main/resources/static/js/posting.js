$(document).on('change', ':file', function() {
	var input = $(this),
		numFiles = input.get(0).files ? input.get(0).files.length : 1,
		label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.parent().parent().next(':text').val(label);

	var files = !!this.files ? this.files : [];
	if (!files.length || !window.FileReader) return; // no file selected, or no FileReader support
	if (/^image/.test(files[0].type)) { // only image file
		var reader = new FileReader(); // instance of the FileReader
		reader.readAsDataURL(files[0]); // read the local file
		reader.onloadend = function() { // set image data as background of div
			input.parent().parent().parent().prev('.imagePreview').css("background-image", "url(" + this.result + ")");
		}
	}
});

$(function() {
	$('#bigword').bind('change', function() {
		var bigwordindex = $(this).prop('selectedIndex') - 1;
		console.log(bigwordindex);
		if (bigwordindex < 0) {
			$('#smword select').removeClass('on');
		} else {
			$('#smword select:not(:eq(bigwordindex)) option').attr('selected', false);
			$('#smword select:not(:eq(bigwordindex))').removeClass('on');
			$('#smword select').eq(bigwordindex).addClass('on');
		}
	});
});

$(function() {

	$('button#add').click(function() {

		var tr_form = '' +
			'<tr>' +
			'<td><input type="text" th:field="*{materialList[].materialName}" class="form-control"></td>' +
			'<td><input type="text" th:field="*{materialList[].materialQuantity}" class="form-control"></td>' +
			'</tr>';
		$(tr_form).appendTo($('table > tbody'));

	});


});


const $img = document.getElementById('img');
const $warning = document.querySelector('.warning');
const $submit = document.getElementById('submit');

$img.onchange = (e) => {
	if ($img.files.length > 0) {
		/// ファイルサイズ取得
		const fileSize = $img.files[0].size;
		/// MB単位のファイルサイズ計算
		const fileMib = fileSize / 1024 ** 2;
		if (fileMib <= 5) {
			/// アップロードファイルを処理。
			/// 例えばサーバー側に保存したりとか...
			$warning.innerText = '';
			$submit.disabled = false;
		} else {
			/// 失敗。送信無効化・エラー表示
			$warning.innerText = '最大サイズは5MBまで!!';
			$submit.disabled = true;
		}
	}
}
