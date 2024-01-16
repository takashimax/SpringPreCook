package com.example.demo.entity.converter;

import com.example.demo.constant.db.ItemCategoryKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * ユーザー情報 権限フィールドConverterクラス
 * 
 * @author ys-fj
 *
 */
@Converter
public class ItemCategoryConverter implements AttributeConverter<ItemCategoryKind, String> {

	/**
	 * 引数で受け取った権限種別Enumを、権限種別のコード値に変換します。
	 * 
	 * @param 権限種別Enum
	 * @return 引数で受け取った権限種別Enumに保管されているコード値
	 */
	@Override
	public String convertToDatabaseColumn(ItemCategoryKind itemCategoryKind) {
		return itemCategoryKind.getCode();
	}

	/**
	 * 引数で受け取った権限種別のコード値を、権限種別Enumに変換します。
	 * 
	 * @param 権限種別のコード値
	 * @return 引数で受け取った権限種別のコード値から逆引きした権限種別Enum
	 */
	@Override
	public ItemCategoryKind convertToEntityAttribute(String value) {
		return ItemCategoryKind.from(value);
	}
}