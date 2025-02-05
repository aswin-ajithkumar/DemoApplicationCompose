package com.example.demoapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permissions() {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver{_, event ->
                if (event == Lifecycle.Event.ON_RESUME){
                    permissionState.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        permissionState.permissions.forEach { permision ->
            when (permision.permission) {
                android.Manifest.permission.CAMERA -> {
                    when {
                        permision.status.isGranted -> {
                            Text(text = "Camera Permission Accepted")
                        }
                        permision.status.shouldShowRationale -> {
                            Text(text = "Camera Permission Rejected")
                        }
                        permision.isPermanentlyDenied()->{
                            Text(text = "Camera Permission Permanently Denied")
                        }
                    }
                }
                android.Manifest.permission.RECORD_AUDIO -> {
                    when {
                        permision.status.isGranted -> {
                            Text(text = "Audio Permission Accepted")
                        }
                        permision.status.shouldShowRationale -> {
                            Text(text = "Audio Permission Rejected")
                        }
                        permision.isPermanentlyDenied()->{
                            Text(text = "Audio Permission Permanently Denied")
                        }
                    }
                }
//                android.Manifest.permission.CAMERA.plus(android.Manifest.permission.RECORD_AUDIO) -> {
//                    when{
//                        permision.status.isGranted -> {
//                            MultiSelectScreen()
//                        }
//                    }
//                }
            }
        }
    }
}