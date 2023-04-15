package com.example.questionnaire

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questionnairebackstage.App
import com.example.questionnairebackstage.data.QuizViewModel
import com.example.questionnairebackstage.data.database.Result
import com.example.questionnairebackstage.ui.navigate.RouteName
import com.example.questionnairebackstage.ui.screen.DatabaseScreen
import com.example.questionnairebackstage.ui.screen.FinishScreen
import com.example.questionnairebackstage.ui.screen.ImportScreen
import com.example.questionnairebackstage.ui.screen.ResultScreen
import kotlin.concurrent.thread

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RouteName.HOME_SCREEN,
    quizViewModel: QuizViewModel = viewModel()
) {
    val viewState by quizViewModel.stateFlow.collectAsState()
    var results:List<Result> = listOf()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RouteName.HOME_SCREEN) {
            HomeScreen(
                onNavigateToDatabase = {
                    quizViewModel.intoDatabaseState()
                    thread { results = App.db.resultDao().getAll() }
                    navController.navigate(RouteName.DATABASE_SCREEN)
                },
                onNavigateToImport = {
                    quizViewModel.intoImportState()
                    navController.navigate(RouteName.IMPORT_SCREEN)
                }
            )
        }
        composable(RouteName.DATABASE_SCREEN) {
            DatabaseScreen(
                results = results,
                navController = navController,
                quizViewModel = quizViewModel
            )
        }
        composable(RouteName.RESULT_SCREEN) {
            ResultScreen(
                onNavigateToDatabaseScreen = {
                    navController.navigate(RouteName.DATABASE_SCREEN) {
                        popUpTo(RouteName.DATABASE_SCREEN) {
                            inclusive = true
                        }
                    }
                },
                quizViewModel = quizViewModel
            )
        }
        composable(RouteName.IMPORT_SCREEN) {
            ImportScreen(
                onNavigateToPrevious = {
                    navController.navigate(RouteName.HOME_SCREEN)
                    { popUpTo(RouteName.HOME_SCREEN) { inclusive = true } }
                },
                onNavigateToNext = {
                    navController.navigate(RouteName.FINISH_SCREEN)
                }
            )
        }
        composable(RouteName.FINISH_SCREEN) {
            FinishScreen(
                quizViewModel = quizViewModel,
                onNavigateToReturn = {
                    when (viewState.isDatabase) {
                        false -> navController.navigate(RouteName.HOME_SCREEN) {
                            popUpTo(RouteName.HOME_SCREEN) {
                                inclusive = true
                            }
                        }

                        true -> navController.navigate(RouteName.DATABASE_SCREEN) {
                            popUpTo(
                                RouteName.DATABASE_SCREEN
                            ) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}