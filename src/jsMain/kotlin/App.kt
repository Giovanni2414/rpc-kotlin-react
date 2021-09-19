import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.html.js.*
import kotlinx.coroutines.*
import kotlinx.html.ButtonType
import kotlinx.html.FormMethod
import kotlinx.html.InputType
import kotlinx.html.onClick

private val scope = MainScope()

val App = functionalComponent<RProps> { _ ->
    val (usersList, setUsersList) = useState(emptyList<UserListItem>())

    useEffect {
        scope.launch {
            setUsersList(getUsersList())
        }
    }

    h1 {
        +"Full-Stack Shopping List"
    }
    ul {
        usersList.sortedByDescending(UserListItem::username).forEach { item ->
            li {
                /*key = item.toString()
                +"[${item.priority}] ${item.desc} "*/
            }
            attrs.onClickFunction = {
                scope.launch {
                    searchUserListItem(item)
                    setUsersList(getUsersList())
                }
            }
        }
    }
    form(action = "/login", method = FormMethod.post) {
        input(type = InputType.text, name = "Username") {

        }
        input(type = InputType.password, name = "Password") {

        }
        button(type = ButtonType.submit) {

        }
    }
    /*child(
        InputComponent,
        props = jsObject {
            onSubmit = { input ->
                val cartItem = ShoppingListItem(input.replace("!", ""), input.count { it == '!' })
                scope.launch {
                    addUserListItem(cartItem)
                    setShoppingList(getShoppingList())
                }
            }
        }
    )*/

}