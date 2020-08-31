 /*----------------------------------------------------------------------
  * プログラム名    ：ImportFilesJobTest.java
  * 作成日          ：2020/04/28
  * 作成者          ：(名前)/IBM
  *----------------------------------------------------------------------
  * 修正履歴 (No.: 修正日: 担当者: 修正内容)
  *  No.01: 2020/04/28: (名前)/IBM: 新規作成。
  *----------------------------------------------------------------------
  */

 package jp.co.fly.job.usersImport;

 import jp.co.fly.model.mapper.PrefectureDao;
 import jp.co.fly.model.mapper.UserDao;
 import jp.co.fly.model.mapper.UserInfoDao;
 import org.junit.Assert;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.mockito.MockitoAnnotations;
 import org.springframework.batch.core.JobExecution;
 import org.springframework.batch.test.JobLauncherTestUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.test.context.ContextConfiguration;
 import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 /**
  * 最初の１文はクラスの機能を敬体で簡潔に記述し、「。」か「.」で終えます。 詳細な説明があれば続けて記述します。自由に折り返すことができます。
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration(classes = {BatchConfigurationForTest.class,
     ImportFilesJob.class,
     ImportPrefProcessor.class,
     ImportReader.class,
     ImportUsersInfoProcessor.class,
     ImportUsersJobListener.class,
     ImportUsersProcessor.class,
     ImportUserStepListener.class,
     ImportUserStepSkipper.class,
     ImportWriter.class
 })
 public class ImportFilesJobTest {

   @Autowired
   private JobLauncherTestUtils jobLauncherTestUtils;

   @MockBean
   private PrefectureDao prefectureDao;
   @MockBean
   private UserDao userDao;
   @MockBean
   private UserInfoDao userInfoDao;

   @Test
   public void testJob() throws Exception {
     JobExecution jobExecution = jobLauncherTestUtils.launchJob();
     Assert.assertEquals("COMPLETED",
         jobExecution.getExitStatus().getExitCode());
   }

//   @Test
//   public void testImportPrefStep() {
//     JobExecution jobExecution = jobLauncherTestUtils
//         .launchStep("importPrefStep");
//     Assert.assertEquals("COMPLETED",
//         jobExecution.getExitStatus().getExitCode());
//   }
 }
