package com.library.ui;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize services
        BookService bookService = new BookService();
        MemberService memberService = new MemberService();
        TransactionService transactionService = new TransactionService();

        while (true) {
            System.out.println("\n============== LIBRARY MANAGEMENT SYSTEM =============");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Add Member");
            System.out.println("4. View All Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View All Transactions");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            // Handle invalid input safely
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input! Please enter a number (1–8).");
                continue;
            }

            switch (choice) {
                case 1 -> { // Add Book
                    System.out.print("Enter Book Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = sc.nextLine();

                    // ID is auto-generated in DB
                    Book book = new Book(0, name, author, true);
                    bookService.addBook(book);
                }

                case 2 -> { // View Books
                    List<Book> books = bookService.getAllBooks();
                    if (books.isEmpty()) System.out.println("No books found!");
                    else books.forEach(System.out::println);
                }

                case 3 -> { // Add Member
                    System.out.print("Enter Member Name: ");
                    String memberName = sc.nextLine();
                    System.out.print("Enter Member Type (Student/Reader): ");
                    String memberType = sc.nextLine();

                    Member member = new Member(0, memberName, memberType, LocalDate.now());
                    memberService.addMember(member);
                }

                case 4 -> { // View Members
                    List<Member> members = memberService.getAllMembers();
                    if (members.isEmpty()) System.out.println("No members found!");
                    else members.forEach(System.out::println);
                }

                case 5 -> { // Issue Book
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    Transaction transaction = new Transaction(
                            0, // auto-generated transaction ID
                            bookId,
                            memberId,
                            LocalDate.now(),
                            null,
                            0.0
                    );
                    transactionService.issueBook(transaction);
                }

                case 6 -> { // Return Book
                    System.out.print("Enter Transaction ID to return: ");
                    int transactionId = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    transactionService.returnBook(transactionId, LocalDate.now());
                }

                case 7 -> { // View All Transactions
                    List<Transaction> transactions = transactionService.getAllTransactions();
                    if (transactions.isEmpty()) System.out.println("No transactions found!");
                    else transactions.forEach(System.out::println);
                }

                case 8 -> { // Exit
                    System.out.println("Exiting system... ");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
