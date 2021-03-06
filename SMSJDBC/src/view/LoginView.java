package view;
import service.AccountService;
import service.StudentService;
import service.impl.AccountServiceImpl;
import service.impl.StudentServiceImpl;
import utils.KeyboardInputContainer;
import utils.Tools;
public  class LoginView  implements KeyboardInputContainer {
    AccountService accountService = new AccountServiceImpl();
    StudentService studentService=new StudentServiceImpl();
    public void login() {
        System.out.println("欢迎使用学生学籍管理系统");
        for (String login_menu : BaseView.LOGIN_MENU) {
            System.out.print(login_menu+":");
            inputStatements.add(sc.nextLine());
        }
        boolean flag = accountService.getAccount((String) inputStatements.get(0), (String) inputStatements.get(1));
        inputStatements.clear();
        Tools.judge(flag,
                () -> {
                    System.out.println("登录成功");
                    studentService.getAllStudent().forEach(System.out::println);
                    new MainView().mainMenu();
                },
                () -> {
                    System.out.println("登录失败！账号与密码输入错误");
                    login();
                }
        );
    }
}
