package jp.co.fly.job.usersImport;

import java.util.List;
import jp.co.fly.job.usersExport.ExportUsersStepListener;
import jp.co.fly.model.entity.UsersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * ステップ処理リスナーの実装クラス
 *
 * @author YuChen
 * @version 1.0
 * @since 2020/2/12
 */
@Component
public class ImportUserStepListener {

  private static final Logger log = LoggerFactory.getLogger(ExportUsersStepListener.class);

  /**
   * ステップの前処理を実装する。
   * <pre>
   *   StepExecutionListener Interface
   * </pre>
   *
   * @param stepExecution 実行ステップ
   */
  @BeforeStep
  public void beforeStep(StepExecution stepExecution) {
    log.info("STEP Start ...");
  }

  /**
   * ステップの後処理を実装する。
   * <pre>
   *   StepExecutionListener Interface
   * </pre>
   *
   * @param stepExecution
   * @return ExitStatus
   */
  @AfterStep
  public ExitStatus afterStep(StepExecution stepExecution) {
    log.info("STEP End ...");
    return stepExecution.getExitStatus();
  }

  /**
   * チャンクの前処理を実装する。
   * <pre>
   *   ChunkListener Interface
   * </pre>
   *
   * @param context
   */
  @BeforeChunk
  public void beforeChunk(ChunkContext context) {
    log.debug("CHUNK Start ...");
  }

  /**
   * チャンクの後処理を実装する。
   * <pre>
   *   ChunkListener Interface
   * </pre>
   *
   * @param context
   */
  @AfterChunk
  public void afterChunk(ChunkContext context) {
    log.debug("CHUNK End ...");
  }

  /**
   * チャンクのエラー処理を実装する。
   * <pre>
   *   ChunkListener Interface
   * </pre>
   *
   * @param context
   */
  @AfterChunkError
  public void afterChunkError(ChunkContext context) {
    log.error("CHUNK exception occurred!" + context);
  }

  /**
   * Readerの前処理を実装する。
   * <pre>
   *   ItemReadListener Interface
   * </pre>
   */
  @BeforeRead
  public void beforeRead() {
    log.debug("READ Start ...");
  }

  /**
   * Readerのエラー処理を実装する。
   * <pre>
   *   ItemReadListener Interface
   * </pre>
   *
   * @param ex
   */
  @OnReadError
  public void onReadError(Exception ex) {
    log.error("READ exception occurred by: " + ex.getMessage());
  }

  /**
   * Readerの後処理を実装する。
   * <pre>
   *   ItemReadListener Interface
   * </pre>
   *
   * @param usersEntity
   */
  @AfterRead
  public void afterRead(UsersEntity usersEntity) {
    log.debug("READ End ...");
  }

  /**
   * Processorの前処理を実装する。
   * <pre>
   *   ItemProcessListener Interface
   * </pre>
   *
   * @param usersEntity item
   */
  @BeforeProcess
  public void beforeProcess(UsersEntity usersEntity) {
    log.debug("PROCESS Start ...");
  }

  /**
   * Processorの後処理を実装する。
   * <pre>
   *   ItemProcessListener Interface
   * </pre>
   *
   * @param inUserEntity
   * @param outUserEntity
   */
  @AfterProcess
  public void afterProcess(UsersEntity inUserEntity, UsersEntity outUserEntity) {
    log.debug("PROCESS End ...");
  }

  /**
   * Processorのエラー処理を実装する。
   * <pre>
   *   ItemProcessListener Interface
   * </pre>
   *
   * @param usersEntity
   * @param ex
   */
  @OnProcessError
  public void onProcessError(UsersEntity usersEntity, Exception ex) {
    log.error("PROCESS exception occurred by: " + usersEntity.username);
  }

  /**
   * Writerの前処理を実装する。
   * <pre>
   *   ItemWriteListener Interface
   * </pre>
   *
   * @param usersEntityList
   */
  @BeforeWrite
  public void beforeWrite(List<UsersEntity> usersEntityList) {
    log.debug("WRITE Start ...");
  }

  /**
   * Writerの後処理を実装する。
   * <pre>
   *   ItemWriteListener Interface
   * </pre>
   *
   * @param usersEntityList
   */
  @AfterWrite
  public void afterWrite(List<UsersEntity> usersEntityList) {
    log.debug("WRITE End ...");
  }

  /**
   * Writerのエラー処理を実装する。
   * <pre>
   *   ItemWriteListener Interface
   * </pre>
   *
   * @param ex
   * @param usersEntityList
   */
  @OnWriteError
  public void onWriteError(Exception ex, List<UsersEntity> usersEntityList) {
    log.error("WRITE exception occurred by: " + ex.getMessage());
  }

}
