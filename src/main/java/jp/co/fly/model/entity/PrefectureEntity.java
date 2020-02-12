package jp.co.fly.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * JpaRepositoryによるテーブルにアクセス用のEntity実装
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Data
@Entity
@Table(name = "mst_prefecture")
public class PrefectureEntity {

  @Id
  @Column(name = "pref_id")
  public String prefId;

  @Column(name = "pref_name")
  public String prefName;

  @Column(name = "pref_capital")
  public String prefCapital;
}
