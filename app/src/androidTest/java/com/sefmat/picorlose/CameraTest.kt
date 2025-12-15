package com.sefmat.picorlose

import android.Manifest
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.sefmat.picorlose.ui.theme.Camera
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class CameraTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.CAMERA)

    @Test
    fun camera_showsButton() {
        composeTestRule.setContent {
            Camera(navController = rememberNavController())
        }
        composeTestRule
            .onNodeWithText("TOMAR FOTO")
            .assertIsDisplayed()
    }
}