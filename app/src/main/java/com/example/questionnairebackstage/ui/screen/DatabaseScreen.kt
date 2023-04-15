package com.example.questionnairebackstage.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.questionnairebackstage.R
import com.example.questionnairebackstage.data.QuizViewModel
import com.example.questionnairebackstage.data.database.Result
import com.example.questionnairebackstage.ui.component.CustomText
import com.example.questionnairebackstage.ui.navigate.RouteName
import com.example.questionnairebackstage.ui.theme.QuestionnaireBackstageTheme

private const val TAG = "DatabaseScreen"

@Composable
fun DatabaseItem(
    name:String = "",
    json:String = "",
    navController: NavController = rememberNavController(),
    quizViewModel: QuizViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp)
            .border(
                width = 1.5f.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                quizViewModel.resultChange(name, json)
                navController.navigate(RouteName.RESULT_SCREEN)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(start = 25.dp, top = 18.dp, bottom = 18.dp)
                .weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(end = 20.dp)
        )
    }
}

@Composable
fun DatabaseScreen(
    results:List<Result> = listOf(),
    navController: NavController = rememberNavController(),
    quizViewModel: QuizViewModel = viewModel()
) {
    BackHandler {
        navController.navigate(RouteName.HOME_SCREEN)
        { popUpTo(RouteName.HOME_SCREEN) { inclusive = true } }
    }
    QuestionnaireBackstageTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column() {
                CustomText(
                    text = stringResource(R.string.questionnaire_database),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 20.dp)
                )
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    for (result in results) {
                        item {
                            DatabaseItem(
                                navController = navController,
                                quizViewModel = quizViewModel,
                                name = result.name,
                                json = result.json
                            )
                        }
                    }
                }
            }
        }
    }
}
