import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase(){
        return (int) questions.stream().count();
    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category){

        return (int) questions.stream().filter(question -> question.getCategory().equals(category)).count();

    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions(){

        return questions.stream().map(Question::getQuestionString).collect(Collectors.toList());

    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category){

        return questions.stream().filter(question -> question.getCategory().equals(category))
                .map(Question::getQuestionString).collect(Collectors.toList());

    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct(){

        return questions.stream().flatMap(question -> question.getAllAnswers().stream()).distinct().collect(Collectors.toList());

    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate){

        return questions.stream().flatMap(question -> question.getAllAnswers().stream()).anyMatch(answerCandidate::equals);

    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate){

        return (int) questions.stream().flatMap(question -> question.getAllAnswers()
                .stream()).filter(question -> question.equals(answerCandidate)).count();

    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory(){

        return questions.stream().collect(Collectors.groupingBy(Question::getCategory,
                Collectors.mapping(Question::getQuestionString, Collectors.toList())));

    }

    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        return questions.stream().filter(question -> question.getCategory().equals(c)).flatMap(question -> question.getAllAnswers()
                .stream()).distinct().reduce("", (s, s2) -> s.length() >= s2.length() ? s : s2);
    }

    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();
    }

}