package com.example.myapplication.view.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.viewmodels.LoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @Composable
    fun ScreenLogin(loginViewModel: LoginViewModel, navigationController: NavHostController) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Close(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), loginViewModel, navigationController)

        }
    }

    @Composable
    fun Close(modifier: Modifier) {
        val activity = LocalContext.current as Activity
        Icon(
            tint = colorResource(id = R.color.purple),
            imageVector = Icons.Default.Close,
            contentDescription = "close",
            modifier = modifier.clickable {
                activity.finish()
            }
        )
    }

    @Composable
    fun Body(
        modifier: Modifier,
        loginViewModel: LoginViewModel,
        navigationController: NavHostController
    ) {
        val email: String by loginViewModel.email.observeAsState(initial = "")
        val password: String by loginViewModel.password.observeAsState(initial = "")

        Column(modifier = modifier) {
            ImageHeader(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.size(16.dp))
            HeaderText()
            Spacer(modifier = Modifier.size(16.dp))
            InputEmail(email) {
                loginViewModel.onLoginChanged(email = it, password = password)
            }
            Spacer(modifier = Modifier.size(16.dp))
            InputPassword(password) {
                loginViewModel.onLoginChanged(email = email, password = it)
            }
            Spacer(modifier = Modifier.size(24.dp))
            LoginButton(navigationController, loginViewModel)
            Spacer(modifier = Modifier.size(16.dp))
            BiometricButton()
        }
    }

    @Composable
    fun ImageHeader(modifier: Modifier) {
        Image(
            painter = painterResource(R.drawable.logosophos),
            contentDescription = "Logo Sophos",
            contentScale = ContentScale.Fit,
            modifier = modifier.size(width = 300.dp, height = 150.dp)
        )
    }

    @Composable
    fun HeaderText() {
        Text(
            text = stringResource(id = R.string.header_text),
            color = colorResource(id = R.color.purple),
            fontSize = 18.sp, fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 80.dp)
        )
    }

    @Composable
    fun InputEmail(email: String, onTextChanged: (String) -> Unit) {
        TextField(
            value = email,
            onValueChange = { onTextChanged(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = null, tint = colorResource(id = R.color.purple)
                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp, SolidColor(colorResource(id = R.color.purple)),
                    MaterialTheme.shapes.large
                ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.email),
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.purple)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(id = R.color.purple),
                backgroundColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }

    @Composable
    fun InputPassword(password: String, onTextChanged: (String) -> Unit) {
        var passwordVisibility by remember { mutableStateOf(false) }
        TextField(
            value = password, onValueChange = { onTextChanged(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.VpnKey,
                    contentDescription = null,
                    tint = colorResource(id = R.color.purple)
                )
            },
            trailingIcon = {
                val image = if (passwordVisibility) {
                    Icons.Default.VisibilityOff
                } else {
                    Icons.Default.Visibility
                }

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = image,
                        contentDescription = "Show Password",
                        tint = colorResource(id = R.color.purple)
                    )
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp, SolidColor(colorResource(id = R.color.purple)),
                    MaterialTheme.shapes.large
                ),
            placeholder = {
                Text(
                    text = stringResource(R.string.password),
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.purple)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(id = R.color.purple),
                backgroundColor = Color.White,
                focusedLabelColor = colorResource(id = R.color.purple),
                focusedIndicatorColor = Color.Transparent
            )
        )
    }



    @Composable
    fun LoginButton(navigationController: NavHostController, loginViewModel: LoginViewModel) {
        val context =LocalContext.current
        Button(
            onClick = {
                loginViewModel.doLogin(navigationController,loginViewModel, context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.purple),
                contentColor = Color.White

            )
        ) {
            Text(
                stringResource(id = R.string.get_into),
                fontSize = 18.sp
            )
        }
    }
    fun mToast(context: Context){
        Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
    }


    @Composable
    fun BiometricButton() {
        OutlinedButton(
            onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = colorResource(id = R.color.purple)
            ),
            border = BorderStroke(2.dp, color = colorResource(id = R.color.purple)),
            shape = MaterialTheme.shapes.large
        ) {
            Icon(painterResource(id = R.drawable.fingerprint), contentDescription = null)
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = stringResource(R.string.biometric),
                fontSize = 18.sp
            )
        }
    }

    @Composable
    fun MySpacer(size: Int) {
        Spacer(modifier = Modifier.height((size.dp)))
    }



}